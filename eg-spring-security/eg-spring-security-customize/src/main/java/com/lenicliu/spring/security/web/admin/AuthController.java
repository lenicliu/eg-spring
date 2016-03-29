package com.lenicliu.spring.security.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lenicliu.spring.security.web.AdminController;

@Controller
@RequestMapping("/admin/auths")
public class AuthController extends AdminController {

	@RequestMapping
	public String list(Model model, String keyword) {
		model.addAttribute("auths", adminService.findAuthList(keyword));
		model.addAttribute("keyword", keyword);
		return "auth/list";
	}
}