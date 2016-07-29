package com.lenicliu.spring.boot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lenicliu.spring.boot.mapper.MessageMapper;
import com.lenicliu.spring.boot.model.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationIntegrationTests {

    @Autowired
    private MessageMapper messageMapper;

    @Test
    public void testProfileRepository() {
        Message message = messageMapper.selectByPrimaryKey(1);
        Assert.assertEquals(message.getAuthor(), "Richard");
    }
}
