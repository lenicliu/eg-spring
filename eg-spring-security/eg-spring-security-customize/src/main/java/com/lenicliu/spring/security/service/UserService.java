package com.lenicliu.spring.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.lenicliu.spring.security.domain.User;

public interface UserService extends UserDetailsService {

	User findById(Long id);

}