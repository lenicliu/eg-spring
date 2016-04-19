package com.lenicliu.spring.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lenicliu.spring.boot.domain.Message;
import com.lenicliu.spring.boot.mapper.MessageMapper;

@SpringBootApplication
@MapperScan("com.lenicliu.spring.boot.mapper")
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private MessageMapper messageMapper;

	@Override
	public void run(String... args) throws Exception {
		Message message = messageMapper.find(new Long(1));
		System.out.println(message);
	}
}