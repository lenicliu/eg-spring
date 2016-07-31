package com.lenicliu.spring.boot;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@WebIntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationIntegrationTests {

	@Test
	public void testHome() {
		String expected = "hi, spring-boot, this is a servlet";
		//driver.get("http://localhost:8080/index");
		//Assert.assertTrue(driver.getPageSource().contains("hi, spring-boot, this is a servlet"));
	}
}