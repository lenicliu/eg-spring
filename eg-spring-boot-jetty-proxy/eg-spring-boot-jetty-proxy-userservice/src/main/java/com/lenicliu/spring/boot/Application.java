package com.lenicliu.spring.boot;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {

	@RequestMapping("/users")
	public Object users() {
		Map<String, Object> user1 = new LinkedHashMap<>();
		user1.put("user_id", 1);
		user1.put("user_name", "lenic liu");
		Map<String, Object> user2 = new LinkedHashMap<>();
		user2.put("user_id", 2);
		user2.put("user_name", "richard cheng");
		return Arrays.asList(user1, user2);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}