package com.lenicliu.spring.boot.listener;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationStartedListener implements ApplicationListener<ApplicationStartedEvent> {

	private Logger	logger	= LoggerFactory.getLogger(getClass());

	@Override
	public void onApplicationEvent(ApplicationStartedEvent event) {
		logger.info("==================ApplicationStartedEvent==================");
		logger.info("event = " + ToStringBuilder.reflectionToString(event));
		logger.info("event.source = " + ToStringBuilder.reflectionToString(event.getSource()));
		logger.info("==================ApplicationStartedEvent==================");
	}
}