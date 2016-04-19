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
	private ConnectionProperties	connection;
	
	@Autowired
	private ApplicationProperties	application;

	@Test
	public void testConnectionProperties() {
		Assert.assertNotNull(connection);
		Assert.assertEquals(connection.getUsername(), "lenicliu");
	}

	@Test
	public void testApplicationProperties() {
		Assert.assertNotNull(application);
		Assert.assertEquals(application.getName(), "spring-boot-properties-application");
	}
}