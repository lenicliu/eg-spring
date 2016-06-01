package com.lenicliu.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ConnectionProperties.class)
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(false).run(args);
	}
	
	@Autowired
	private ConnectionProperties	connection;
	
	@Autowired
	private ApplicationProperties	application;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(connection);
		System.out.println(application);
	}
}