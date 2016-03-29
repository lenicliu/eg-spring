package com.lenicliu.spring.boot.initializer;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

public class ShownApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
	private Logger	logger	= LoggerFactory.getLogger(getClass());

	@Override
	public void initialize(ConfigurableApplicationContext applicationContext) {
		logger.info("==================ShownApplicationContextInitializer==================");
		logger.info("applicationContext = " + ToStringBuilder.reflectionToString(applicationContext));
		logger.info("==================ShownApplicationContextInitializer==================");
	}
}