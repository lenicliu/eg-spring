package com.lenicliu.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.session.ExpiringSession;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.web.http.SessionRepositoryFilter;

@SpringBootApplication
public class Application {

	@Bean(name = "sessionRepositoryFilter")
	public FilterRegistrationBean sessionRepositoryFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		bean.setFilter(new SessionRepositoryFilter<ExpiringSession>(new MapSessionRepository()));
		bean.addUrlPatterns("/*");
		return bean;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}