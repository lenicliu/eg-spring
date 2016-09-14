package com.lenicliu.spring.boot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class ApplicationIntegrationTests {

    @Test
    public void tests() {
        TestRestTemplate restTemplate = new TestRestTemplate();
        String response = restTemplate.getForObject("http://localhost:8080/persons", String.class);
        Assert.assertTrue(response.contains("lenicliu"));
        Assert.assertTrue(response.contains("richard"));
        response = restTemplate.getForObject("http://localhost:8080/logs", String.class);
        Assert.assertTrue(response.contains("do sth."));
    }
}