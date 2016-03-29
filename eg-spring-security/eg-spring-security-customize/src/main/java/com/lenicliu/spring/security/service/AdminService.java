package com.lenicliu.spring.security.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenicliu.spring.security.domain.Authority;
import com.lenicliu.spring.security.domain.Role;
import com.lenicliu.spring.security.domain.User;
import com.lenicliu.spring.security.repository.AuthorityRepository;
import com.lenicliu.spring.security.repository.RoleRepository;
import com.lenicliu.spring.security.repository.UserRepository;

@Service
@Transactional
public class AdminService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AuthorityRepository authorityRepository;

	public void createUser(User user, List<Long> role_ids) {
		user.setId(null);
		userRepository.insert(user);
		userRepository.insertUserRoles(user.getId(), role_ids);
	}

	public void updateUser(User user, List<Long> role_ids) {
		userRepository.update(user);
		userRepository.deleteUserRoles(user.getId());
		userRepository.insertUserRoles(user.getId(), role_ids);
	}

	public void deleteUser(Long id) {
		userRepository.delete(id);
		userRepository.deleteUserRoles(id);
	}

	public List<User> findUserList(String keyword) {
		keyword = keyword == null ? "%" : "%" + keyword.trim() + "%";
		return userRepository.findList(keyword);
	}

	public void createRole(Role role, List<Long> auth_ids) {
		roleRepository.insert(role);
		roleRepository.insertRoleAuthorities(role.getId(), auth_ids);
	}

	public void updateRole(Role role, List<Long> auth_ids) {
		roleRepository.update(role);
		roleRepository.deleteRoleAuthorities(role.getId());
		roleRepository.insertRoleAuthorities(role.getId(), auth_ids);
	}

	public void deleteRole(Long id) {
		roleRepository.delete(id);
		roleRepository.deleteRoleAuthorities(id);
		roleRepository.deleteUserRoles(id);
	}

	public List<Role> findRoleList(String keyword) {
		keyword = keyword == null ? "%" : "%" + keyword.trim() + "%";
		return roleRepository.findList(keyword);
	}

	public List<Role> findAllRoles() {
		return findRoleList(null);
	}

	public void createAuth(Authority authority) {
		authorityRepository.insert(authority);
	}

	public void updateAuth(Authority authority) {
		authorityRepository.update(authority);
	}

	public void deleteAuth(Long id) {
		authorityRepository.delete(id);
		authorityRepository.deleteRoleAuthorities(id);
	}

	public List<Authority> findAuthList(String keyword) {
		keyword = keyword == null ? "%" : "%" + keyword.trim() + "%";
		return authorityRepository.findList(keyword);
	}

	public User findUserById(Long id) {
		try {
			return id == null ? null : userRepository.findById(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public List<Long> findRoleIdsByUserId(Long user_id) {
		if (user_id == null) {
			return Collections.emptyList();
		}
		List<Role> roles = roleRepository.findByUserId(user_id);
		List<Long> role_ids = new ArrayList<Long>();
		for (Role role : roles) {
			role_ids.add(role.getId());
		}
		return role_ids;
	}

	public Role findRoleById(Long id) {
		try {
			return id == null ? null : roleRepository.findById(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	public List<Authority> findAllAuths() {
		return findAuthList(null);
	}

	public Object findAuthIdsByRoleId(Long role_id) {
		if (role_id == null) {
			return Collections.emptyList();
		}
		List<Authority> auths = authorityRepository.findByRoleId(role_id);
		List<Long> auth_ids = new ArrayList<Long>();
		for (Authority auth : auths) {
			auth_ids.add(auth.getId());
		}
		return auth_ids;
	}
}