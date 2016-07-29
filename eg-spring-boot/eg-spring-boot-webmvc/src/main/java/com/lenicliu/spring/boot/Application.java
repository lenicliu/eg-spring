package com.lenicliu.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;

@SpringBootApplication
public class Application {

	@Bean
	public EmbeddedServletContainerCustomizer customizeContainerr() {
		return new CustomizedContainer();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	private static class CustomizedContainer implements EmbeddedServletContainerCustomizer {
		@Override
		public void customize(ConfigurableEmbeddedServletContainer container) {
			container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404.ftl"));
			container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error.ftl"));
		}
	}
}