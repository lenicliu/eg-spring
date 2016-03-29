package com.lenicliu.spring.security.web.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lenicliu.spring.security.domain.Role;
import com.lenicliu.spring.security.web.AdminController;

@Controller
@RequestMapping("/admin/roles")
public class RoleController extends AdminController {

	@RequestMapping
	public String list(Model model, String keyword) {
		model.addAttribute("roles", adminService.findRoleList(keyword));
		model.addAttribute("keyword", keyword);
		return "role/list";
	}

	@RequestMapping("/input")
	public String input(Model model, Long id) {
		model.addAttribute("role", adminService.findRoleById(id));
		model.addAttribute("auth_ids", adminService.findAuthIdsByRoleId(id));
		model.addAttribute("auths", adminService.findAllAuths());
		return "role/input";
	}

	@RequestMapping("/view")
	public String view(Model model, Long id) {
		model.addAttribute("readonly", "readonly");
		model.addAttribute("disabled", "disabled");
		return input(model, id);
	}

	@RequestMapping("/submit")
	public String submit(Role role, @RequestParam(value = "auth_ids", required = false) List<Long> auth_ids) {
		if (role.getId() != null && role.getId() > 0) {
			adminService.updateRole(role, auth_ids);
		} else {
			adminService.createRole(role, auth_ids);
		}
		return "redirect:";
	}

	@RequestMapping("/delete")
	public String delete(Long id) {
		adminService.deleteRole(id);
		return "redirect:";
	}
}