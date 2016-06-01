package com.lenicliu.spring.boot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest
public class ApplicationIntegrationTests {

	private RestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void testHtml() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/user", String.class);
		Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
		Assert.assertTrue(response.getHeaders().getContentType().includes(MediaType.TEXT_HTML));
	}

	@Test
	public void testJson() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/user.json", String.class);
		Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
		Assert.assertTrue(response.getHeaders().getContentType().includes(MediaType.APPLICATION_JSON));
	}

	@Test
	public void testXml() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/user.xml", String.class);
		Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
		Assert.assertTrue(response.getHeaders().getContentType().includes(MediaType.APPLICATION_XML));
	}
}