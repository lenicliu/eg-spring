package com.lenicliu.spring.boot;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {
	@JmsListener(destination = "spring-boot-jms")
	public String resolveMessage(String message) {
		return "Hey, lenicliu: " + message;
	}
}