package com.lenicliu.spring.boot;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application {
	@RequestMapping("/")
	public Object index() {
		return Collections.singletonMap("status", "ok");
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public EmbeddedServletContainerFactory container() throws IOException {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory();

		// basic settings
		tomcat.setPort(10086);
		tomcat.setContextPath("/api");
		tomcat.setSessionTimeout(1, TimeUnit.MINUTES);

		// apache + tomcat (ajp)
		tomcat.addAdditionalTomcatConnectors(new Connector() {
			{
				this.setProtocol("AJP/1.3");
				this.setPort(10087);
				this.setSecure(false);
				this.setAllowTrace(false);
				this.setScheme("http");
				this.setRedirectPort(10088);
			}
		});

		// support ssl
		tomcat.addAdditionalTomcatConnectors(new Connector("org.apache.coyote.http11.Http11NioProtocol") {
			{
				this.setScheme("https");
				this.setSecure(true);
				this.setPort(10089);
				Http11NioProtocol protocol = (Http11NioProtocol) this.getProtocolHandler();
				protocol.setSSLEnabled(true);
				protocol.setKeystoreFile(new ClassPathResource("keystore").getFile().getAbsolutePath());
				protocol.setKeystorePass("lenicliu");
				protocol.setTruststoreFile(new ClassPathResource("keystore").getFile().getAbsolutePath());
				protocol.setTruststorePass("lenicliu");
				protocol.setKeyAlias("lenicliu");
			}
		});

		return tomcat;
	}
}