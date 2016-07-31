package com.lenicliu.spring.boot;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment=WebEnvironment.DEFINED_PORT)
public class ApplicationIntegrationTests {

	private TestRestTemplate restTemplate = new TestRestTemplate();

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