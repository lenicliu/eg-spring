package com.lenicliu.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@Controller
public class Application {

	@RequestMapping("/")
	public @ResponseBody String greetings() {
		return "hi,spring boot";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}