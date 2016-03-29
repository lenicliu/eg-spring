package com.lenicliu.spring.boot;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {

	@RequestMapping("/")
	public Object index() {
		return Collections.singletonMap("username", "lenicliu");
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
