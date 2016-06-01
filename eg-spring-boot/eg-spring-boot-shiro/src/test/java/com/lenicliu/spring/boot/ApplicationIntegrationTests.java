package com.lenicliu.spring.boot;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@WebIntegrationTest("server.port:0")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class ApplicationIntegrationTests {

	private WebDriver	driver;
	@Value("${local.server.port}")
	private int			port;

	@Before
	public void before() {
		driver = new ChromeDriver();
	}

	@After
	public void after() {
		 driver.close();
	}

	private String url(String path) {
		path = path == null ? "/" : path;
		if (!path.startsWith("/")) {
			path = "/" + path;
		}
		return "http://localhost:" + port + path;
	}

	@Test
	public void testIndex() throws Exception {
		{
			driver.get(url("/"));
			List<WebElement> elements = driver.findElements(By.tagName("a"));
			Assert.assertEquals(3, elements.size());
			Assert.assertTrue(driver.getPageSource().contains("/login\">login</a>"));
			Assert.assertFalse(driver.getPageSource().contains("/logout\">logout</a>"));
		}

		{
			TimeUnit.SECONDS.sleep(2);
			driver.get(url("/home"));
			Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
		}

		{
			TimeUnit.SECONDS.sleep(2);
			driver.get(url("/admin"));
			Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
		}

		{
			TimeUnit.SECONDS.sleep(2);
			WebElement form = driver.findElement(By.tagName("form"));
			Assert.assertNotNull(form);
			Assert.assertTrue(form.getText().contains("admin/567890,lenic/123456"));

			WebElement username = form.findElement(By.name("username"));
			WebElement password = form.findElement(By.name("password"));
			Assert.assertNotNull(username);
			Assert.assertNotNull(password);

			TimeUnit.SECONDS.sleep(2);
			username.sendKeys("lenic");
			TimeUnit.SECONDS.sleep(1);
			password.sendKeys("123456");

			TimeUnit.SECONDS.sleep(2);
			form.submit();

			Assert.assertTrue(driver.getCurrentUrl().contains("index"));
			Assert.assertTrue(driver.getPageSource().contains("Hi,lenic"));
			Assert.assertTrue(driver.getPageSource().contains("/logout\">logout</a>"));
		}

		{
			TimeUnit.SECONDS.sleep(2);
			driver.get(url("/home"));
			Assert.assertTrue(driver.getCurrentUrl().contains("/home"));
			Assert.assertTrue(driver.getPageSource().contains("<h1>Personal</h1>"));
			Assert.assertTrue(driver.getPageSource().contains("<li><a href=\"javascript:void(0);\">my orders</a></li>"));
		}

		{
			TimeUnit.SECONDS.sleep(2);
			driver.get(url("/admin"));
			Assert.assertTrue(driver.getCurrentUrl().contains("/forbidden"));
			Assert.assertTrue(driver.getPageSource().contains("<h1>Tip</h1>"));
			Assert.assertTrue(driver.getPageSource().contains("Not permitted"));
		}

		{
			TimeUnit.SECONDS.sleep(2);
			driver.get(url("/logout"));
			List<WebElement> elements = driver.findElements(By.tagName("a"));
			Assert.assertEquals(3, elements.size());
			Assert.assertTrue(driver.getPageSource().contains("/login\">login</a>"));
			Assert.assertFalse(driver.getPageSource().contains("/logout\">logout</a>"));
		}

		{
			TimeUnit.SECONDS.sleep(2);
			driver.get(url("/login"));
			WebElement form = driver.findElement(By.tagName("form"));
			Assert.assertNotNull(form);
			Assert.assertTrue(form.getText().contains("admin/567890,lenic/123456"));

			WebElement username = form.findElement(By.name("username"));
			WebElement password = form.findElement(By.name("password"));
			Assert.assertNotNull(username);
			Assert.assertNotNull(password);

			TimeUnit.SECONDS.sleep(2);
			username.sendKeys("admin");
			TimeUnit.SECONDS.sleep(1);
			password.sendKeys("567890");

			TimeUnit.SECONDS.sleep(2);
			form.submit();

			Assert.assertTrue(driver.getCurrentUrl().contains("index"));
			Assert.assertTrue(driver.getPageSource().contains("Hi,admin"));
			Assert.assertTrue(driver.getPageSource().contains("/logout\">logout</a>"));
		}

		{
			TimeUnit.SECONDS.sleep(2);
			driver.get(url("/home"));
			Assert.assertTrue(driver.getCurrentUrl().contains("/home"));
			Assert.assertTrue(driver.getPageSource().contains("<h1>Personal</h1>"));
			Assert.assertTrue(driver.getPageSource().contains("<li><a href=\"javascript:void(0);\">my orders</a></li>"));
		}

		{
			TimeUnit.SECONDS.sleep(2);
			driver.get(url("/admin"));
			Assert.assertTrue(driver.getCurrentUrl().contains("/admin"));
			Assert.assertTrue(driver.getPageSource().contains("<h1>Admin</h1>"));
			Assert.assertTrue(driver.getPageSource().contains("<li><a href=\"javascript:void(0);\">Item 4</a></li>"));
		}

		{
			TimeUnit.SECONDS.sleep(2);
			driver.get(url("/logout"));
			List<WebElement> elements = driver.findElements(By.tagName("a"));
			Assert.assertEquals(3, elements.size());
			Assert.assertTrue(driver.getPageSource().contains("/login\">login</a>"));
			Assert.assertFalse(driver.getPageSource().contains("/logout\">logout</a>"));
		}
	}
}