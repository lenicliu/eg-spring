package com.lenicliu.spring.boot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@WebIntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationIntegrationTests {

	@Autowired
	private UserService remoteUserService;

	@Test
	public void testUserService() {
		String user = remoteUserService.find(new Long(1));
		Assert.assertTrue(remoteUserService.toString().contains("HTTP invoker proxy"));
		Assert.assertNotNull(user);
		Assert.assertEquals("LenicLiu", user);
	}

	@Configuration
	static class RemoteConfiguration {
		@Bean(name = "remoteUserService")
		public UserService remoteUserService() {
			HttpInvokerProxyFactoryBean factory = new HttpInvokerProxyFactoryBean();
			factory.setServiceUrl("http://localhost:8080/UserService");
			factory.setServiceInterface(UserService.class);
			factory.afterPropertiesSet();
			return (UserService) factory.getObject();
		}
	}
}