package com.lenicliu.spring.security.bean;

import com.lenicliu.spring.security.domain.Message;
import com.lenicliu.spring.security.domain.User;

public class MessageBO extends Message {

	private User user;

	public MessageBO(Message message, User user) {
		super(message);
		this.user = user;
	}

	@Override
	public String toString() {
		return "MessageBO [user=" + user + ", toString()=" + super.toString() + "]";
	}

	public User getUser() {
		return user;
	}
}