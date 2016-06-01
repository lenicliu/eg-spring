package com.lenicliu.spring.boot;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

	@RequestMapping(value = { "/", "/index" })
	public String index() {
		return "index";
	}

	@ResponseBody
	@RequestMapping("/users")
	public List<Map<String, Object>> users() {
		List<Map<String, Object>> users = new ArrayList<>();
		users.add(newUser(1, "lenic"));
		users.add(newUser(2, "richard"));
		return users;
	}

	private Map<String, Object> newUser(int id, String username) {
		Map<String, Object> user1 = new LinkedHashMap<>();
		user1.put("id", id);
		user1.put("username", username);
		return user1;
	}
}