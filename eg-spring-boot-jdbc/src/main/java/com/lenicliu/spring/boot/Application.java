package com.lenicliu.spring.boot;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.ModelAndView;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@Controller
public class Application {

	@Autowired
	private JdbcTemplate	jdbcTemplate;

	@Bean(name = "characterEncodingFilter")
	public FilterRegistrationBean characterEncodingFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.addInitParameter("encoding", "UTF-8");
		bean.addInitParameter("forceEncoding", "true");
		bean.setFilter(new CharacterEncodingFilter());
		bean.addUrlPatterns("/*");
		return bean;
	}

	@RequestMapping("/")
	public String enter() {
		return "redirect:index";
	}

	@RequestMapping("/404")
	public String _404() {
		return "404";
	}

	@RequestMapping("/index")
	public ModelAndView index() {
		String sql = "SELECT * FROM MESSAGE ORDER BY CREATED DESC";
		List<Message> messages = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Message>(Message.class));

		return new ModelAndView("index", "messages", messages);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(Message message) {
		Assert.hasText(message.getId(), "No id?");
		jdbcTemplate.update("DELETE FROM MESSAGE WHERE ID = ?", message.getId());
		return "redirect:index";
	}

	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	public String submit(Message message) {
		Assert.hasText(message.getAuthor(), "please input author");
		Assert.hasText(message.getEmail(), "please input email");
		Assert.hasText(message.getContent(), "please input content");

		Assert.isTrue(message.getAuthor().length() < 20, "author length must less than 20");
		Assert.isTrue(message.getEmail().length() < 20, "email length must less than 20");
		Assert.isTrue(message.getContent().length() < 2000, "content length must less than 2000");

		String sql = "SELECT MAX(ID) FROM MESSAGE";
		String max = jdbcTemplate.queryForObject(sql, new SingleColumnRowMapper<String>(String.class));

		if (!StringUtils.hasText(max)) {
			max = "0";
		}

		String id = String.format("%06d", Integer.parseInt(max) + 1);
		Date created = new Date();
		message.setId(id);
		message.setAuthor(StringUtils.trimWhitespace(message.getAuthor()));
		message.setEmail(StringUtils.trimWhitespace(message.getEmail()));
		message.setCreated(created);

		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate).withTableName("message");
		insert.execute(new BeanPropertySqlParameterSource(message));

		return "redirect:index";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public EmbeddedServletContainerCustomizer customizeContainerr() {
		return new CustomizedContainer();
	}

	public static class CustomizedContainer implements EmbeddedServletContainerCustomizer {
		@Override
		public void customize(ConfigurableEmbeddedServletContainer container) {
			container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
		}
	}

	public static class Message {
		private String	id;
		private String	author;
		private String	email;
		private String	content;
		private Date	created;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getAuthor() {
			return author;
		}

		public void setAuthor(String author) {
			this.author = author;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Date getCreated() {
			return created;
		}

		public void setCreated(Date created) {
			this.created = created;
		}
	}
}