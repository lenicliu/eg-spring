package com.lenicliu.spring.cloud;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableEurekaClient
@SpringBootApplication
public class Application {

	@RequestMapping("/books")
	public Object books() {
		Map<String, Object> book1 = new LinkedHashMap<>();
		book1.put("book_id", 1);
		book1.put("book_name", "Java in Action");
		Map<String, Object> book2 = new LinkedHashMap<>();
		book2.put("book_id", 2);
		book2.put("book_name", "Work without Spring");
		return Arrays.asList(book1, book2);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}