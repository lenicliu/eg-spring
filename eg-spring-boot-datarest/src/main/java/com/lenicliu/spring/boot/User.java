package com.lenicliu.spring.boot;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="tb_user")
public class User implements Serializable {
	private static final long	serialVersionUID	= -666370648525121137L;
	
	@Id
	@GeneratedValue
	private Long				id;
	
	@Column
	private String				name;
	
	@Column
	private String				email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}