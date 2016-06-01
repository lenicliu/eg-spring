package com.lenicliu.spring.boot.listener;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;

public class ApplicationPreparedListener implements ApplicationListener<ApplicationPreparedEvent> {

	private Logger	logger	= LoggerFactory.getLogger(getClass());

	@Override
	public void onApplicationEvent(ApplicationPreparedEvent event) {
		logger.info("==================ApplicationEnvironmentPreparedEvent==================");
		logger.info("event = " + ToStringBuilder.reflectionToString(event));
		logger.info("event.source = " + ToStringBuilder.reflectionToString(event.getSource()));
		logger.info("==================ApplicationEnvironmentPreparedEvent==================");
	}
}