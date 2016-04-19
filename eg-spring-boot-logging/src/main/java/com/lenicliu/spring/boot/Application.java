package com.lenicliu.spring.boot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private org.slf4j.Logger		slf4j	= org.slf4j.LoggerFactory.getLogger(getClass());
	private org.apache.log4j.Logger	log4j	= org.apache.log4j.Logger.getLogger(getClass());


	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(false).run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		slf4j.debug("this is slf4j debug");
		slf4j.info("this is slf4j info");
		slf4j.warn("this is slf4j warn");
		slf4j.error("this is slf4j error");
		
		log4j.debug("this is log4j debug");
		log4j.info("this is log4j info");
		log4j.warn("this is log4j warn");
		log4j.error("this is log4j error");
	}
}