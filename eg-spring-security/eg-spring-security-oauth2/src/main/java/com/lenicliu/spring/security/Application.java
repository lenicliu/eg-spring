package com.lenicliu.spring.security;

import java.security.Principal;
import java.util.Arrays;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.filter.CompositeFilter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Controller
	public static class HomeController {
		@RequestMapping("/home")
		public String home() {
			return "home";
		}
	}

	@RestController
	@RequestMapping("/api")
	public static class ApiController {
		@RequestMapping("/profile")
		public Principal profile(Principal principal) {
			return principal;
		}
	}

	@Configuration
	public static class WebMvcConfiguration extends WebMvcConfigurerAdapter {
		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			super.addViewControllers(registry);
			registry.addViewController("/").setViewName("index");
			registry.addViewController("/index").setViewName("index");
		}
	}

	@Configuration
	@EnableOAuth2Client
	public static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
		@Autowired
		private OAuth2ClientContext context;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()//
					.antMatcher("/**").authorizeRequests()//
					.antMatchers("/", "/index", "/signin/**", "/webjars/**").permitAll()//
					.anyRequest().authenticated()//
					.and().logout().logoutSuccessUrl("/").permitAll()//
					.and().addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
		}

		@Bean
		@ConfigurationProperties("github.client")
		public OAuth2ProtectedResourceDetails github() {
			return new AuthorizationCodeResourceDetails();
		}

		@Bean
		@ConfigurationProperties("github.resource")
		public ResourceServerProperties githubResource() {
			return new ResourceServerProperties();
		}

		private Filter ssoFilter() {
			String userInfoUri = githubResource().getUserInfoUri();
			String clientId = github().getClientId();

			OAuth2RestTemplate template = new OAuth2RestTemplate(github(), context);
			UserInfoTokenServices tokenServices = new UserInfoTokenServices(userInfoUri, clientId);

			OAuth2ClientAuthenticationProcessingFilter filter = //
					new OAuth2ClientAuthenticationProcessingFilter("/signin/github");
			filter.setRestTemplate(template);
			filter.setTokenServices(tokenServices);

			CompositeFilter compositefilter = new CompositeFilter();
			compositefilter.setFilters(Arrays.asList(filter));
			return compositefilter;
		}

		@Bean
		public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
			FilterRegistrationBean registration = new FilterRegistrationBean();
			registration.setFilter(filter);
			registration.setOrder(-100);
			return registration;
		}
	}
}