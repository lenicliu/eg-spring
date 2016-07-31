package com.lenicliu.spring.boot;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean(name = "indexServlet")
	public ServletRegistrationBean indexServlet() {
		ServletRegistrationBean bean = new ServletRegistrationBean();
		bean.setServlet(new IndexServlet());
		bean.addUrlMappings("/index");
		return bean;
	}

	public static class IndexServlet extends HttpServlet {
		private static final long	serialVersionUID	= -8944347685885474860L;

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doPost(req, resp);
		}

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			resp.setContentType("text/html");
			resp.getWriter().write("hi, spring-boot, this is a servlet<br/>");
			resp.getWriter().write("req.getCharacterEncoding() = " + req.getCharacterEncoding() + "<br/>");
			resp.getWriter().write("resp.getCharacterEncoding() = " + resp.getCharacterEncoding() + "<br/>");
			resp.getWriter().flush();
		}
	}
}