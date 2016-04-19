package com.lenicliu.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class Application implements CommandLineRunner {
	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(false).run(args);
	}
	
	@Autowired
	private Producer producer;

	@Override
	public void run(String... args) throws Exception {
		System.out.println(producer.sendMessage("You are right."));
	}
}