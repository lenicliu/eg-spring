package com.lenicliu.spring.boot;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index(Map<String, Object> model) {
		model.put("message", "Hello Spring Boot");
		return "index";
	}

}