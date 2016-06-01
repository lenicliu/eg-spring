package com.lenicliu.spring.boot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationIntegrationTests {

	@Autowired
	private Producer producer;

	@Test
	public void testProfileRepository() {
		Assert.assertTrue(producer.sendMessage("You are right.").startsWith("Hey, lenicliu: "));
	}
}