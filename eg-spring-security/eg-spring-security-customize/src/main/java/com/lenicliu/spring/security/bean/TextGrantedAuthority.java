package com.lenicliu.spring.security.bean;

import org.springframework.security.core.GrantedAuthority;

public class TextGrantedAuthority implements GrantedAuthority {

	private static final long serialVersionUID = 8042594620133601996L;

	private String name;

	public TextGrantedAuthority(String name) {
		super();
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TextGrantedAuthority other = (TextGrantedAuthority) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TextGrantedAuthority [name=" + name + "]";
	}
}