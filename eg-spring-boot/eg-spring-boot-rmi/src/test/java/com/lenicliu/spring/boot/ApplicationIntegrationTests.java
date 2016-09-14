package com.lenicliu.spring.boot;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ApplicationIntegrationTests {

    private ConfigurableApplicationContext web;
    private ConfigurableApplicationContext remote;

    @Autowired
    private UserService remoteUserService;

    @Before
    public void setup() {
        this.web = SpringApplication.run(Application.class);
        this.remote = new SpringApplicationBuilder(RemoteConfiguration.class).web(false).run();
    }

    @After
    public void clean() {
        this.remote.close();
        this.web.close();
    }

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
