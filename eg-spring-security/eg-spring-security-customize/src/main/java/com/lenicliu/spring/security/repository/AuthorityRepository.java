package com.lenicliu.spring.security.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.lenicliu.spring.security.domain.Authority;

@Repository
public class AuthorityRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<Authority> rowMapper() {
		return new BeanPropertyRowMapper<Authority>(Authority.class);
	}

	public Authority findById(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM TB_AUTHORITY WHERE ID = ?", rowMapper(), id);
	}

	public List<Authority> findList(String keyword) {
		keyword = keyword == null ? "%" : "%" + keyword.trim() + "%";
		return jdbcTemplate.query("SELECT * FROM TB_AUTHORITY WHERE NAME LIKE ? ORDER BY NAME ASC", rowMapper(), keyword);
	}
	
	public List<Authority> findByRoleId(Long role_id){
		return jdbcTemplate.query("SELECT a.* FROM TB_AUTHORITY a, TB_RVA r WHERE a.ID = r.AUTH_ID and r.ROLE_ID = ?", rowMapper(), role_id);
	}

	public void insert(Authority authority) {
		authority.setId(null);
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("TB_AUTHORITY").usingGeneratedKeyColumns("ID");
		Number key = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(authority));
		authority.setId(key.longValue());
	}

	public void update(Authority authority) {
		jdbcTemplate.update("UPDATE TB_AUTHORITY SET NAME = ? WHERE ID = ?", authority.getName(), authority.getId());
	}

	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM TB_AUTHORITY WHERE ID = ?", id);
	}
	
	public void deleteRoleAuthorities(Long auth_id){
		jdbcTemplate.update("DELETE FROM TB_RVA WHERE AUTH_ID = ?", auth_id);
	}
}