package com.lenicliu.spring.security;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.DefaultOAuth2RequestAuthenticator;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RequestAuthenticator;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
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
	@EnableAutoConfiguration
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

		public static class ClientResource {
			private OAuth2ProtectedResourceDetails client = new AuthorizationCodeResourceDetails();
			private ResourceServerProperties resource = new ResourceServerProperties();

			public OAuth2ProtectedResourceDetails getClient() {
				return client;
			}

			public ResourceServerProperties getResource() {
				return resource;
			}
		}

		@Bean
		@ConfigurationProperties("github")
		public ClientResource github() {
			return new ClientResource();
		}

		@Bean
		@ConfigurationProperties("google")
		public ClientResource google() {
			return new ClientResource();
		}

		public static class GoogleOAuth2RequestAuthenticator extends DefaultOAuth2RequestAuthenticator {
			@Override
			public void authenticate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext clientContext,
					ClientHttpRequest request) {
				OAuth2AccessToken accessToken = clientContext.getAccessToken();
				String tokenType = OAuth2AccessToken.BEARER_TYPE;
				request.getHeaders().set("Authorization", String.format("%s %s", tokenType, accessToken.getValue()));
			}
		}

		private Filter ssoFilter() {

			List<Filter> filters = new ArrayList<>();
			filters.add(filter("/signin/github", github(), null));
			filters.add(filter("/signin/google", google(), new GoogleOAuth2RequestAuthenticator()));

			CompositeFilter compositefilter = new CompositeFilter();
			compositefilter.setFilters(filters);
			return compositefilter;
		}

		private OAuth2ClientAuthenticationProcessingFilter filter(String url, ClientResource resource,
				OAuth2RequestAuthenticator authenticator) {
			OAuth2ProtectedResourceDetails client = resource.getClient();
			String uri = resource.getResource().getUserInfoUri();

			OAuth2RestTemplate template = new OAuth2RestTemplate(client, context);
			UserInfoTokenServices tokenServices = new UserInfoTokenServices(uri, client.getClientId());

			if (authenticator != null) {
				template.setAuthenticator(authenticator);
			}

			OAuth2ClientAuthenticationProcessingFilter filter = //
					new OAuth2ClientAuthenticationProcessingFilter(url);
			filter.setRestTemplate(template);
			filter.setTokenServices(tokenServices);
			return filter;
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