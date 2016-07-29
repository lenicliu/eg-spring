package com.lenicliu.spring.boot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lenicliu.spring.boot.domain.Message;
import com.lenicliu.spring.boot.mapper.MessageMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationIntegrationTests {

	@Autowired
	private MessageMapper messageMapper;

	@Test
	public void testMessageMapper() {
		Message message = messageMapper.findMessage(new Long(1));
		Assert.assertEquals(message.getAuthor(), "Richard");
	}
}