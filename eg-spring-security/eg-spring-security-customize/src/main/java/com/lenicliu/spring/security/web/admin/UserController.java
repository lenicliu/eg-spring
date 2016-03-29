package com.lenicliu.spring.security.web.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lenicliu.spring.security.domain.User;
import com.lenicliu.spring.security.web.AdminController;

@Controller
@RequestMapping("/admin/users")
public class UserController extends AdminController {

	@RequestMapping
	public String list(Model model, String keyword) {
		model.addAttribute("users", adminService.findUserList(keyword));
		model.addAttribute("keyword", keyword);
		return "user/list";
	}

	@RequestMapping("/input")
	public String input(Model model, Long id) {
		model.addAttribute("user", adminService.findUserById(id));
		model.addAttribute("role_ids", adminService.findRoleIdsByUserId(id));
		model.addAttribute("roles", adminService.findAllRoles());
		return "user/input";
	}

	@RequestMapping("/view")
	public String view(Model model, Long id) {
		model.addAttribute("readonly", "readonly");
		model.addAttribute("disabled", "disabled");
		return input(model, id);
	}

	@RequestMapping("/submit")
	public String submit(User user, @RequestParam(value = "role_ids", required = false) List<Long> role_ids) {
		if (user.getId() != null && user.getId() > 0) {
			adminService.updateUser(user, role_ids);
		} else {
			adminService.createUser(user, role_ids);
		}
		return "redirect:";
	}

	@RequestMapping("/delete")
	public String delete(Long id) {
		adminService.deleteUser(id);
		return "redirect:";
	}
}