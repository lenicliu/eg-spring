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
    }
    
    @Test
    public void testMetrics(){
    	ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/metrics", String.class);
    	Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    public void testAutoconfig(){
    	ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/autoconfig", String.class);
    	Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    public void testBeans(){
    	ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/beans", String.class);
    	Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    public void testEnv(){
    	ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/env", String.class);
    	Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    public void testMappings(){
    	ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/mappings", String.class);
    	Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    public void testInfo(){
    	ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/info", String.class);
    	Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    	Assert.assertTrue(response.getBody().contains("This is a spring boot metrics demo"));
    }
    
    @Test
    public void testConfigprops(){
    	ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/configprops", String.class);
    	Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    public void testHealth(){
    	ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/health", String.class);
    	Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    public void testDump(){
    	ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/dump", String.class);
    	Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    }
    
    @Test
    public void testTrace(){
    	ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/trace", String.class);
    	Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
    }
}