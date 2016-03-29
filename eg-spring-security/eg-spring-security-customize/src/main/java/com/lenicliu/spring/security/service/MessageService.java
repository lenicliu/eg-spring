package com.lenicliu.spring.security.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lenicliu.spring.security.bean.MessageBO;
import com.lenicliu.spring.security.domain.Message;
import com.lenicliu.spring.security.repository.MessageRepository;

@Service
@Transactional
public class MessageService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private MessageRepository messageRepository;

	@Autowired
	private UserService userService;

	public List<MessageBO> findList(String keyword) {
		List<MessageBO> messageList = new ArrayList<MessageBO>();
		try {
			(messageRepository.findList(keyword)).forEach((message) -> {
				messageList.add(new MessageBO(message, userService.findById(message.getUid())));
			});
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return messageList;
	}

	public MessageBO findById(Long id) {
		if (id == null) {
			return null;
		}
		Message message = messageRepository.findById(id);
		if (message == null) {
			return null;
		}
		return new MessageBO(message, userService.findById(message.getUid()));
	}

	public void deleteMessage(Long id) {
		messageRepository.delete(id);
	}

	public void createMessage(Message message) {
		message.setCreated(new Date());
		message.setUpdated(new Date());
		messageRepository.insert(message);
	}

	public void updateMessage(Message message) {
		message.setUpdated(new Date());
		messageRepository.update(message);
	}
}