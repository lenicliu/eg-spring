package com.lenicliu.spring.boot;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableJdbcHttpSession
public class Application {
	@RequestMapping("/")
	public Object session(HttpSession httpSession) {
		Map<String, Object> session = new HashMap<String, Object>();
		session.put("id", httpSession.getId());
		session.put("create_time", httpSession.getCreationTime());
		session.put("last_accessed_time", httpSession.getLastAccessedTime());
		session.put("max_inactive_interval", httpSession.getMaxInactiveInterval());
		session.put("class", httpSession.getClass());
		session.put("attrs", attributes(httpSession));
		return session;
	}

	private Map<String, Object> attributes(HttpSession httpSession) {
		Enumeration<String> names = httpSession.getAttributeNames();
		Map<String, Object> attrs = new HashMap<String, Object>();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			attrs.put(name, httpSession.getAttribute(name));
		}
		return attrs;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}