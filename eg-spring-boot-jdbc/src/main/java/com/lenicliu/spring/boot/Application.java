package com.lenicliu.spring.boot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class).web(false).run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		String sql = "SELECT * FROM MESSAGE";
		RowMapper<Message> rowMapper = new BeanPropertyRowMapper<Message>(Message.class);
		List<Message> messages = jdbcTemplate.query(sql, rowMapper);
		messages.forEach((m) -> System.out.println(m));
	}
}