package com.lenicliu.spring.boot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class ApplicationIntegrationTests {

	@Test
	public void testHome() {
		// String expected = "hi, spring-boot, this is a servlet";
		// driver.get("http://localhost:8080/index");
		// Assert.assertTrue(driver.getPageSource().contains("hi, spring-boot,
		// this is a servlet"));
	}
}