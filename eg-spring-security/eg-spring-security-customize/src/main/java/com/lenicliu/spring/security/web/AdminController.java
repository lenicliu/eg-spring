package com.lenicliu.spring.security.web;

import org.springframework.beans.factory.annotation.Autowired;

import com.lenicliu.spring.security.service.AdminService;

public abstract class AdminController extends WebController {

	@Autowired
	protected AdminService adminService;
}