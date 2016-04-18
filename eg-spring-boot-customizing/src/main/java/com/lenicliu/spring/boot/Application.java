package com.lenicliu.spring.boot;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.lenicliu.spring.boot.generator.SuccessExitCodeGenerator;
import com.lenicliu.spring.boot.initializer.ShownApplicationContextInitializer;
import com.lenicliu.spring.boot.listener.ApplicationEnvironmentPreparedListener;
import com.lenicliu.spring.boot.listener.ApplicationPreparedListener;
import com.lenicliu.spring.boot.listener.ApplicationStartedListener;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication application = new SpringApplication(Application.class);
		application.addInitializers(new ShownApplicationContextInitializer());
		application.addListeners(new ApplicationStartedListener());
		application.addListeners(new ApplicationEnvironmentPreparedListener());
		application.addListeners(new ApplicationPreparedListener());
		ConfigurableApplicationContext context = application.run(args);
		TimeUnit.SECONDS.sleep(3);
		SpringApplication.exit(context, new SuccessExitCodeGenerator());
	}
}