package com.lenicliu.spring.cloud;

import java.net.URI;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableScheduling
@EnableDiscoveryClient
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

@RestController
class ScheduleLoad {
	@Autowired
	@LoadBalanced
	private RestTemplate restTemplate;

	@RequestMapping("/")
	public List<Map<String, Object>> load() {
		ResponseEntity<List<Map<String, Object>>> exchange = this.restTemplate.exchange("http://book-service/books",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Map<String, Object>>>() {
				}, (Object) "mstine");
		return exchange.getBody();
	}
}

@Component
class Discovery implements CommandLineRunner {
	@Autowired
	private DiscoveryClient discoveryClient;

	@Override
	public void run(String... strings) throws Exception {
		discoveryClient.getInstances("book-service").forEach((ServiceInstance client) -> {
			String host = client.getHost();
			int port = client.getPort();
			String serviceId = client.getServiceId();
			URI uri = client.getUri();
			System.out.println(String.format("%s, %s, %s, %s", serviceId, host, port, uri));
		});
	}
}