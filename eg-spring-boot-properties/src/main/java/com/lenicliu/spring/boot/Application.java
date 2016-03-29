package com.lenicliu.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@Controller
@EnableConfigurationProperties(ConnectionProperties.class)
public class Application {

	@Autowired
	private ConnectionProperties	connection;

	@Autowired
	private ApplicationProperties	application;

	@RequestMapping("/connection")
	public @ResponseBody ConnectionProperties connection() {
		return connection;
	}

	@RequestMapping("/application")
	public @ResponseBody ApplicationProperties application() {
		return application;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}