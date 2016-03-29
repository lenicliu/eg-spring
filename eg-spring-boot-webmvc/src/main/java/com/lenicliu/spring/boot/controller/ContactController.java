package com.lenicliu.spring.boot.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lenicliu.spring.boot.model.Contact;

@Controller
public class ContactController {

	@RequestMapping("/contact")
	public @ResponseBody List<Contact> contactJSON() {
		return Arrays.asList(new Contact("张三", "13312341234"), new Contact("李四", "13945672345"));
	}

	@RequestMapping("/contact.html")
	public ModelAndView contactHtml() {
		List<Contact> contacts = Arrays.asList(new Contact("张三", "13312341234"), new Contact("李四", "13945672345"));
		return new ModelAndView("contact", "contactList", contacts);
	}
}