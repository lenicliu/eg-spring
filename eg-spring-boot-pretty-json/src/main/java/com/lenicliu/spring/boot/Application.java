package com.lenicliu.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	public static void main(String[] args) {
//		System.setProperty("spring.config.location", "classpath:base-application.properties");
//		System.setProperty("spring.config.location", "classpath:pretty-application.properties");
//		System.setProperty("spring.config.location", "classpath:pretty-application.yml");
		System.setProperty("spring.config.location", "classpath:alexanoid-application.yml");
		SpringApplication.run(Application.class, args);
	}
}