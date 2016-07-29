package com.lenicliu.spring.boot;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class ApplicationIntegrationTests {

	private WebDriver driver;

	@Before
	public void before() {
		driver = new ChromeDriver();
	}

	@After
	public void after() {
		driver.close();
	}

	@Test
	public void testHome() {
		driver.get("http://localhost:8080/index");
		Assert.assertTrue(driver.getPageSource().contains("hi, spring-boot, this is a servlet"));
	}
}