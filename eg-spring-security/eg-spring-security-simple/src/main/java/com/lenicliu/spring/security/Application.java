package com.lenicliu.spring.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

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
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public static class WebSecurity extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
			// use mem user details manager
			InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> authentication = authenticationManagerBuilder.inMemoryAuthentication();
			
			// read-only lenicliu
			authentication.withUser("lenicliu").password("lenicliu")
				.roles("USER").authorities("READ")
				.accountExpired(false).accountLocked(false);
			
			// access-only guest
			authentication.withUser("guest").password("guest")
				.roles().authorities(Collections.emptyList())
				.accountExpired(false).accountLocked(false);
			
			// management admin
			authentication.withUser("admin").password("admin")
				.roles("ADMIN").authorities("READ", "WRITE")
				.accountExpired(false).accountLocked(false);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			// use login form
			http.formLogin();
			
			// disable http basic
			http.httpBasic().disable();
			
			// authorities of write
			http.authorizeRequests().antMatchers("/input", "/delete", "/submit").hasAuthority("WRITE");

			// authorities of read
			http.authorizeRequests().antMatchers("/view").hasAuthority("READ");

			// authorities of access
			http.authorizeRequests().anyRequest().authenticated();

			// disable csrf
			http.csrf().disable();
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
	
	/**
	 * simple controller
	 * 
	 * @author lenicliu
	 */
	@Controller
	public static class WebController {

		@Autowired
		private MessageService messageService;

		@RequestMapping("/")
		public String index(Model model) {
			model.addAttribute("messages", messageService.findAll());
			return "index";
		}

		@RequestMapping("/input")
		public String input(Model model, Long id) {
			Message message = messageService.find(id);
			model.addAttribute("message", message);
			return "input";
		}

		@RequestMapping("/view")
		public String view(Model model, Long id) {
			Message message = messageService.find(id);
			model.addAttribute("message", message);
			return "view";
		}

		@RequestMapping("/submit")
		public String submit(Long id, String content) {
			if (id != null) {
				messageService.updateMessage(id, content);
			} else {
				messageService.createMessage(content);
			}
			return "redirect:";
		}

		@RequestMapping("/delete")
		public String delete(Long id) {
			messageService.deleteMessage(id);
			return "redirect:";
		}
	}

	/**
	 * simple service implement message crud
	 * 
	 * @author lenicliu
	 *
	 */
	@Component
	public static class MessageService {
		private List<Message>		messages	= new ArrayList<Message>();
		private static AtomicLong	ID			= new AtomicLong(1);

		public MessageService() {
			messages.add(new Message(ID.getAndIncrement(), "Hello world", new Date()));
			messages.add(new Message(ID.getAndIncrement(), "Hello spring boot", new Date()));
			messages.add(new Message(ID.getAndIncrement(), "This is a spring security simple", new Date()));
		}

		public Message find(Long id) {
			if (id == null) {
				return null;
			}
			for (Message message : messages) {
				if (id.equals(message.getId())) {
					return message;
				}
			}
			return null;
		}

		public List<Message> findAll() {
			return new ArrayList<Message>(messages);
		}

		public void createMessage(String message) {
			messages.add(new Message(ID.getAndIncrement(), (message == null ? "" : message.trim()), new Date()));
		}

		public void updateMessage(Long id, String content) {
			if (id != null) {
				for (Message message : messages) {
					if (id.equals(message.getId())) {
						message.setMessage(content);
					}
				}
			}
		}

		public void deleteMessage(Long id) {
			if (id != null) {
				Message found = null;
				for (Message message : messages) {
					if (id.equals(message.getId())) {
						found = message;
						break;
					}
				}
				if (found != null) {
					messages.remove(found);
				}
			}
		}
	}

	/**
	 * simple message entity
	 * 
	 * @author lenicliu
	 *
	 */
	public static class Message {
		public Message(Long id, String message, Date created) {
			super();
			this.id = id;
			this.message = message;
			this.created = created;
		}

		private Long	id;
		private String	message;
		private Date	created;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Date getCreated() {
			return created;
		}

		public void setCreated(Date created) {
			this.created = created;
		}
	}
}