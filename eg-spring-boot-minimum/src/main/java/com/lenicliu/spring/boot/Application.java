package com.lenicliu.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@SpringBootApplication
public class Application {

	@RequestMapping("/")
	public @ResponseBody String greetings() {
		return "hi,spring boot";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}