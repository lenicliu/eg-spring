package com.lenicliu.spring.boot.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import com.lenicliu.spring.boot.domain.Message;
import com.lenicliu.spring.boot.mapper.MessageMapper;

@Service
@Transactional
public class MessageService {

	@Autowired
	private MessageMapper	messageMapper;

	public List<Message> findMessage() {
		return messageMapper.findMessage();
	}

	public void deleteMessage(Message message) {
		Assert.notNull(message, "No Infomation?");
		Assert.hasText(message.getId(), "No id?");
		messageMapper.deleteMessage(message.getId());
	}

	public void createMessage(Message message) {
		Assert.hasText(message.getAuthor(), "please input author");
		Assert.hasText(message.getEmail(), "please input email");
		Assert.hasText(message.getContent(), "please input content");

		Assert.isTrue(message.getAuthor().length() < 20, "author length must less than 20");
		Assert.isTrue(message.getEmail().length() < 20, "email length must less than 20");
		Assert.isTrue(message.getContent().length() < 2000, "content length must less than 2000");

		String max = messageMapper.findMaxId();

		if (!StringUtils.hasText(max)) {
			max = "0";
		}

		String id = String.format("%06d", Integer.parseInt(max) + 1);
		Date created = new Date();
		message.setId(id);
		message.setAuthor(StringUtils.trimWhitespace(message.getAuthor()));
		message.setEmail(StringUtils.trimWhitespace(message.getEmail()));
		message.setCreated(created);

		messageMapper.createMessage(message);
	}
}