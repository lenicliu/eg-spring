package com.lenicliu.spring.security.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.lenicliu.spring.security.domain.Message;

@Repository
public class MessageRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<Message> rowMapper() {
		return new BeanPropertyRowMapper<Message>(Message.class);
	}

	public Message findById(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM TB_MESSAGE WHERE ID = ?", rowMapper(), id);
	}

	public List<Message> findList(String keyword) {
		keyword = keyword == null ? "%" : "%" + keyword.trim() + "%";
		return jdbcTemplate.query("SELECT * FROM TB_MESSAGE WHERE CONTENT LIKE ? ORDER BY CREATED DESC", rowMapper(), keyword);
	}

	public void insert(Message message) {
		message.setId(null);
		message.setCreated(new Date());
		message.setUpdated(new Date());
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("TB_MESSAGE").usingGeneratedKeyColumns("ID");
		Number key = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(message));
		message.setId(key.longValue());
	}

	public void update(Message message) {
		jdbcTemplate.update("UPDATE TB_MESSAGE SET CONTENT = ?, UPDATED = ? WHERE ID = ?", message.getContent(), new Date(), message.getId());
	}

	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM TB_MESSAGE WHERE ID = ?", id);
	}
}