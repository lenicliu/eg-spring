package com.lenicliu.spring.boot.mapper;

import com.lenicliu.spring.boot.domain.Message;

public interface MessageMapper {

	public Message find(Long Id);
	
}