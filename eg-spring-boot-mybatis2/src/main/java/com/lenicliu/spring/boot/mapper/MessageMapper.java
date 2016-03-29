package com.lenicliu.spring.boot.mapper;

import java.util.List;

import com.lenicliu.spring.boot.domain.Message;

public interface MessageMapper {

	public List<Message> findMessage();
	
	public String findMaxId();

	public void deleteMessage(String id);

	public void createMessage(Message message);
}