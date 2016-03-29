package com.lenicliu.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;

@SpringBootApplication
public class Application {

	@Autowired
	private UserService userService;

	@Bean(name = "/UserService")
	public HttpInvokerServiceExporter userService() {
		HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
		exporter.setServiceInterface(UserService.class);
		exporter.setService(userService);
		return exporter;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}