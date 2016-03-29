package com.lenicliu.spring.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping("/404.ftl")
	public String _404() {
		return "404";
	}
	
	@RequestMapping("/error500")
	public String _500() throws Exception {
		throw new Exception();
	}
}