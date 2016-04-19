package com.lenicliu.spring.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	@Autowired
	private JmsMessagingTemplate template;

	public String sendMessage(String message) {
		return template.convertSendAndReceive("spring-boot-jms", message, String.class);
	}
}