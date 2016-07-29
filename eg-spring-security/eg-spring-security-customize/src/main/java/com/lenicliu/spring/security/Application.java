package com.lenicliu.spring.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

import com.lenicliu.spring.security.service.UserService;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Application.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * spring security configuration
	 * 
	 * @author lenicliu
	 */
	@EnableWebSecurity
	@EnableGlobalMethodSecurity(prePostEnabled = true)
	public static class WebSecurity extends WebSecurityConfigurerAdapter {
		private Logger logger = LoggerFactory.getLogger(getClass());

		@Autowired
		private UserService userService;

		@Override
		protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
			authenticationManagerBuilder.userDetailsService(userService);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// customize login form
			http.formLogin()
					// customize url
					.defaultSuccessUrl("/").failureUrl("/login?error").loginPage("/login").loginProcessingUrl("/j_spring_security_check")
					// customize parameter
					.passwordParameter("j_password").usernameParameter("j_username");

			// customize logout
			http.logout()
					// customize url
					.logoutUrl("/logout").logoutSuccessUrl("/login?logout")
					// session & authentication
					.invalidateHttpSession(true).clearAuthentication(true)
					// extra handler
					.addLogoutHandler((LogoutHandler) (request, response, authentication) -> {
						if (authentication != null) {
							logger.info(authentication.toString());
						}
					});

			// disable http basic
			http.httpBasic().disable();

			// granted matcher
			http.authorizeRequests().antMatchers("/admin/users").hasAuthority("USER_READ");
			http.authorizeRequests().antMatchers("/admin/users/view").hasAuthority("USER_READ");
			http.authorizeRequests().antMatchers("/admin/users/**").hasAuthority("USER_WRITE");
			http.authorizeRequests().antMatchers("/admin/roles").hasAuthority("ROLE_READ");
			http.authorizeRequests().antMatchers("/admin/roles/view").hasAuthority("ROLE_READ");
			http.authorizeRequests().antMatchers("/admin/roles/**").hasAuthority("ROLE_WRITE");
			http.authorizeRequests().antMatchers("/admin/auths").hasAuthority("AUTH_READ");

			http.authorizeRequests().antMatchers("/messages/view").hasAuthority("MSG_READ");
			http.authorizeRequests().antMatchers("/messages/input").hasAuthority("MSG_WRITE");
			http.authorizeRequests().antMatchers("/messages/submit").hasAuthority("MSG_WRITE");
			http.authorizeRequests().antMatchers("/messages/delete").hasAuthority("MSG_WRITE");
			
			// http.authorizeRequests().antMatchers("/messages/more").hasAuthority("MSG_WRITE");

			// resources/static files
			http.authorizeRequests().antMatchers("/login", "/j_spring_security_check", "/webjars/**").permitAll();

			// others authenticated
			http.authorizeRequests().anyRequest().authenticated();

			// enable csrf
			// http.csrf().disable();
			
			// max session for each user
			http.sessionManagement().maximumSessions(1);
		}
	}

	/**
	 * Customize Servlet Container
	 * 
	 * @author lenicliu
	 */
	@Component
	public static class ServletContainer implements EmbeddedServletContainerCustomizer {
		@Override
		public void customize(ConfigurableEmbeddedServletContainer container) {
			container.addErrorPages(new ErrorPage(HttpStatus.FORBIDDEN, "/403.jsp"));
			container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.jsp"));
			container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.jsp"));
		}
	}
}