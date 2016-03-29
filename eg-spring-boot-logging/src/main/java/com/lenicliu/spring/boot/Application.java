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

	private org.slf4j.Logger		slf4j	= org.slf4j.LoggerFactory.getLogger(getClass());
	private org.apache.log4j.Logger	log4j	= org.apache.log4j.Logger.getLogger(getClass());

	@RequestMapping("/log")
	public @ResponseBody String log() {
		slf4j.debug("this is slf4j debug");
		slf4j.info("this is slf4j info");
		slf4j.warn("this is slf4j warn");
		slf4j.error("this is slf4j error");
		
		log4j.debug("this is log4j debug");
		log4j.info("this is log4j info");
		log4j.warn("this is log4j warn");
		log4j.error("this is log4j error");
		return "See console : log";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}