package com.lenicliu.spring.security.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenicliu.spring.security.bean.DefaultUserDetails;
import com.lenicliu.spring.security.domain.Authority;
import com.lenicliu.spring.security.domain.Role;
import com.lenicliu.spring.security.domain.User;
import com.lenicliu.spring.security.repository.AuthorityRepository;
import com.lenicliu.spring.security.repository.RoleRepository;
import com.lenicliu.spring.security.repository.UserRepository;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = null;
		List<Role> roles = new ArrayList<Role>();
		List<Authority> auths = new ArrayList<Authority>();

		try {
			user = userRepository.findByUsername(username);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		if (user == null) {
			throw new UsernameNotFoundException(username);
		}

		try {
			roles.addAll(roleRepository.findByUserId(user.getId()));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		for (Role role : roles) {
			if (role != null && role.getId() != null) {
				try {
					auths.addAll(authorityRepository.findByRoleId(role.getId()));
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}

		return new DefaultUserDetails(user, roles, auths);
	}

	@Override
	public User findById(Long id) {
		try {
			return id == null ? null : userRepository.findById(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}
}