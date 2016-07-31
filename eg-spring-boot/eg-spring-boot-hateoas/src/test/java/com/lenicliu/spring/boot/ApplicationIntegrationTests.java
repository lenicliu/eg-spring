package com.lenicliu.spring.boot;

import java.util.Date;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lenicliu.spring.boot.Post.State;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.DEFINED_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationIntegrationTests {

	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	public void test01GetPosts() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/posts", String.class);
		Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
	}

	@Test
	public void test02GetPosts1() {
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/posts/1", String.class);
		Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
	}

	@Test
	public void test03PostPosts() {
		Post request = new Post();
		request.setAuthor("lenicliu");
		request.setCreated(new Date());
		request.setState(State.PUBLISH);
		request.setTitle("Hello World.");
		ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/posts", request, String.class);
		Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
		response = restTemplate.getForEntity("http://localhost:8080/posts/4", String.class);
		Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
	}

	@Test
	public void test04PutPosts() {
		Post request = new Post();
		request.setId(1L);
		request.setAuthor("lenicliu");
		request.setCreated(new Date());
		request.setState(State.PUBLISH);
		request.setTitle("Hello World.");
		restTemplate.put("http://localhost:8080/posts/1", request);

		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/posts/1", String.class);
		Assert.assertTrue(response.getStatusCode().is2xxSuccessful());
		Assert.assertTrue(response.getBody().contains("lenicliu"));
	}

	@Test
	public void test05DeletePosts() {
		restTemplate.delete("http://localhost:8080/posts/1");
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/posts/1", String.class);
		Assert.assertTrue(response.getStatusCode().is4xxClientError());// 404 not found
	}
}