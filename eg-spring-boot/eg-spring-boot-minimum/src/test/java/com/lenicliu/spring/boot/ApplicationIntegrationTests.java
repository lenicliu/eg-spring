package com.lenicliu.spring.boot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment=WebEnvironment.DEFINED_PORT)
public class ApplicationIntegrationTests {

    private TestRestTemplate restTemplate = new TestRestTemplate();
    
    @Test
    public void testHome(){
    	ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080", String.class);
    	Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    	Assert.assertTrue(response.getBody().equals("hi,spring boot"));
    }
}