package com.lenicliu.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import com.lenicliu.spring.boot.generator.SuccessExitCodeGenerator;
import com.lenicliu.spring.boot.initializer.ShownApplicationContextInitializer;
import com.lenicliu.spring.boot.listener.ApplicationEnvironmentPreparedListener;
import com.lenicliu.spring.boot.listener.ApplicationPreparedListener;
import com.lenicliu.spring.boot.listener.ApplicationStartedListener;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@Controller
public class Application {

	private static ConfigurableApplicationContext	context	= null;

	public static void main(String[] args) throws Exception {
		SpringApplication application = new SpringApplication(Application.class);
		application.addInitializers(new ShownApplicationContextInitializer());
		application.addListeners(new ApplicationStartedListener());
		application.addListeners(new ApplicationEnvironmentPreparedListener());
		application.addListeners(new ApplicationPreparedListener());
		context = application.run(args);
		long millis = System.currentTimeMillis();
		while (System.currentTimeMillis() - millis < 10 * 1000) {
			Thread.sleep(1);
		}
		SpringApplication.exit(context, new SuccessExitCodeGenerator());
	}
}