package com.lenicliu.spring.boot;

import org.openjdk.jmh.Main;
import org.openjdk.jmh.annotations.Benchmark;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Benchmark
	public void empty() {
		// do nothing
	}

	@Benchmark
	public void calculation() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				for (int k = 0; k < 100; k++) {
					@SuppressWarnings("unused")
					int t = i + j + k;
				}
			}
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main.main(args);
	}
}
