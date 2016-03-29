package com.lenicliu.spring.security.bean;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lenicliu.spring.security.domain.Authority;
import com.lenicliu.spring.security.domain.Role;
import com.lenicliu.spring.security.domain.User;

public class DefaultUserDetails implements UserDetails {

	private static final long serialVersionUID = 9096428924356896280L;

	public DefaultUserDetails(User user, List<Role> roles, List<Authority> auths) {
		super();
		this.user = user;
		this.roles = roles;
		this.auths = auths;
	}

	private User user;
	private List<Role> roles;
	private List<Authority> auths;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> granted = new HashSet<GrantedAuthority>();
		for (Authority auth : getAuths()) {
			if (auth != null && auth.getName() != null) {
				granted.add(new TextGrantedAuthority(auth.getName()));
			}
		}
		for (Role role : getRoles()) {
			if (role != null && role.getName() != null) {
				granted.add(new TextGrantedAuthority(role.getName()));
			}
		}
		return granted;
	}

	@Override
	public String getPassword() {
		return getUser().getPassword();
	}

	@Override
	public String getUsername() {
		return getUser().getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public User getUser() {
		return user;
	}

	@Override
	public String toString() {
		return "DefaultUserDetails [user=" + user + ", roles=" + roles + ", auths=" + auths + "]";
	}

	public List<Role> getRoles() {
		return roles;
	}

	public List<Authority> getAuths() {
		return auths;
	}
}