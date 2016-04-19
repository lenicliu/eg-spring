package com.lenicliu.spring.boot;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {

	@RequestMapping(value = "/user", produces = { "application/xml", "application/json" })
	public @ResponseBody User findUser() {
		return new User("Richard", 16, "男");
	}

	@RequestMapping(value = "/user", produces = { "text/html" })
	public ModelAndView viewUser() {
		return new ModelAndView("user", "user", findUser());
	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//		configurer.favorPathExtension(false);
//		configurer.ignoreAcceptHeader(false);
		configurer.favorPathExtension(true);
		configurer.ignoreAcceptHeader(true);
		configurer.useJaf(false);
		configurer.defaultContentType(MediaType.TEXT_HTML);
		configurer.mediaType("html", MediaType.TEXT_HTML);
		configurer.mediaType("xml", MediaType.APPLICATION_XML);
		configurer.mediaType("json", MediaType.APPLICATION_JSON);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@XmlRootElement
	public static class User {

		private String	name;
		private int		age;
		private String	sex;

		public User() {
			super();
		}

		public User(String name, int age, String sex) {
			super();
			this.name = name;
			this.age = age;
			this.sex = sex;
		}

		@XmlElement
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		@XmlElement
		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		@XmlElement
		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + age;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((sex == null) ? 0 : sex.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			User other = (User) obj;
			if (age != other.age)
				return false;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (sex == null) {
				if (other.sex != null)
					return false;
			} else if (!sex.equals(other.sex))
				return false;
			return true;
		}
	}
}