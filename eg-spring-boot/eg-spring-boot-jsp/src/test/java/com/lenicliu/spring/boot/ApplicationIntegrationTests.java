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
    public void testHome() {
        String url = "http://localhost:8080";
        TestRestTemplate template = new TestRestTemplate();
        String response = template.getForObject(url, String.class);
        Assert.assertTrue(response.contains("Hello Spring Boot"));
    }
    @Test
    public void test404() {
        String url = "http://localhost:8080/notfound";
        TestRestTemplate template = new TestRestTemplate();
        String response = template.getForObject(url, String.class);
        Assert.assertTrue(response.contains("404"));
    }
}
