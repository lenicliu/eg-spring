package com.lenicliu.spring.boot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProfileRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Profile> findAll() {
		String sql = "SELECT USERNAME,PASSWORD,ROLE FROM PROFILE";
		RowMapper<Profile> rowMapper = new BeanPropertyRowMapper<Profile>(Profile.class);
		return jdbcTemplate.query(sql, rowMapper);
	}
}