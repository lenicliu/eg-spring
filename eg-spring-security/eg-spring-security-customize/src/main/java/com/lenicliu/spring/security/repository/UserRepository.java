package com.lenicliu.spring.security.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.lenicliu.spring.security.domain.User;

@Repository
public class UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private BeanPropertyRowMapper<User> rowMapper() {
		return new BeanPropertyRowMapper<User>(User.class);
	}

	public User findByUsername(String username) {
		List<User> users = jdbcTemplate.query("SELECT * FROM TB_USER WHERE USERNAME = ?", rowMapper(), username);
		return users != null && users.size() == 1 ? users.get(0) : null;
	}

	public User findById(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM TB_USER WHERE ID = ?", rowMapper(), id);
	}

	public void insert(User user) {
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("TB_USER").usingGeneratedKeyColumns("ID");
		Number key = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(user));
		user.setId(key.longValue());
	}

	public void deleteUserRoles(Long user_id) {
		jdbcTemplate.update("DELETE FROM TB_UVR WHERE USER_ID = ?", user_id);
	}

	public void insertUserRoles(Long user_id, List<Long> role_ids) {
		if (role_ids != null) {
			for (Long role_id : role_ids) {
				jdbcTemplate.update("INSERT INTO TB_UVR(USER_ID, ROLE_ID)VALUES(?, ?)", user_id, role_id);
			}
		}
	}

	public void update(User user) {
		jdbcTemplate.update("UPDATE TB_USER SET USERNAME = ?, PASSWORD = ? WHERE ID = ?", user.getUsername(), user.getPassword(), user.getId());
	}

	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM TB_USER WHERE ID = ?", id);
	}

	public List<User> findList(String keyword) {
		return jdbcTemplate.query("SELECT * FROM TB_USER WHERE USERNAME LIKE ? ORDER BY USERNAME ASC", rowMapper(), keyword);
	}
}