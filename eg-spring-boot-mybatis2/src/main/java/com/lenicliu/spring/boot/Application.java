package com.lenicliu.spring.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SpringBootApplication
@MapperScan("org.lenic.jboot.mybatis.mapper")
public class Application {

	@RequestMapping("/")
	public String enter() {
		return "redirect:index";
	}
	
	@RequestMapping("/404")
	public String _404() {
		return "404";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public EmbeddedServletContainerCustomizer customizeContainerr() {
		return new CustomizedContainer();
	}

	public static class CustomizedContainer implements EmbeddedServletContainerCustomizer {
		@Override
		public void customize(ConfigurableEmbeddedServletContainer container) {
			container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
		}
	}
}