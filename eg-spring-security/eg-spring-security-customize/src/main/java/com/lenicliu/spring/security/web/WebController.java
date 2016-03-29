package com.lenicliu.spring.security.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

import com.lenicliu.spring.security.bean.DefaultUserDetails;

public abstract class WebController {
	private Logger logger = LoggerFactory.getLogger(getClass());

	protected DefaultUserDetails getCurrentUserDetails() throws Exception {
		try {
			return (DefaultUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception("Can not get current user", e);
		}
	}
}