package com.lenicliu.spring.boot;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

	@RequestMapping("/")
	public Object session(String key, String value, HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		if (StringUtils.hasText(key) && StringUtils.hasText(value)) {
			session.setAttribute(key, value);
		}
		return describe(session);
	}

	private Map<String, Object> describe(HttpSession httpSession) {
		Map<String, Object> session = new HashMap<String, Object>();
		session.put("create_time", httpSession.getCreationTime());
		session.put("last_accessed_time", httpSession.getLastAccessedTime());
		session.put("max_inactive_interval", httpSession.getMaxInactiveInterval());
		Enumeration<String> names = httpSession.getAttributeNames();
		Map<String, Object> attrs = new HashMap<String, Object>();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			attrs.put(name, httpSession.getAttribute(name));
		}
		session.put("id", httpSession.getId());
		session.put("attrs", attrs);
		session.put("class", httpSession.getClass());
		return session;
	}
}