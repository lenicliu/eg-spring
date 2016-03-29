package com.lenicliu.spring.cloud;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {
	@RequestMapping("/users")
	public Object index() {
		return Arrays.asList(new User(1L, "Lenic"), new User(2L, "Richard"));
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	static class User {
		public User(Long id, String username) {
			super();
			this.id = id;
			this.username = username;
		}

		private Long id;
		private String username;

		public Long getId() {
			return id;
		}

		public String getUsername() {
			return username;
		}
	}
}