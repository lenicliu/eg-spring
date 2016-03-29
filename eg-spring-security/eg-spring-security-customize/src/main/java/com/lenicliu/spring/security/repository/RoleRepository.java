package com.lenicliu.spring.security.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.lenicliu.spring.security.domain.Role;

@Repository
public class RoleRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<Role> rowMapper() {
		return new BeanPropertyRowMapper<Role>(Role.class);
	}

	public Role findById(Long id) {
		return jdbcTemplate.queryForObject("SELECT * FROM TB_ROLE WHERE ID = ?", rowMapper(), id);
	}

	public List<Role> findByUserId(Long user_id) {
		return jdbcTemplate.query("SELECT r.* FROM TB_ROLE r, TB_UVR u WHERE r.ID = u.ROLE_ID and u.USER_ID = ?", rowMapper(), user_id);
	}

	public List<Role> findList(String keyword) {
		keyword = keyword == null ? "%" : "%" + keyword.trim() + "%";
		return jdbcTemplate.query("SELECT * FROM TB_ROLE WHERE NAME LIKE ? ORDER BY NAME ASC", rowMapper(), keyword);
	}

	public void insert(Role role) {
		role.setId(null);
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		insert.withTableName("TB_ROLE").usingGeneratedKeyColumns("ID");
		Number key = insert.executeAndReturnKey(new BeanPropertySqlParameterSource(role));
		role.setId(key.longValue());
	}

	public void update(Role role) {
		jdbcTemplate.update("UPDATE TB_ROLE SET NAME = ? WHERE ID = ?", role.getName(), role.getId());
	}

	public void delete(Long id) {
		jdbcTemplate.update("DELETE FROM TB_ROLE WHERE ID = ?", id);
	}

	public void deleteRoleAuthorities(Long role_id) {
		jdbcTemplate.update("DELETE FROM TB_RVA WHERE ROLE_ID = ?", role_id);
	}

	public void insertRoleAuthorities(Long role_id, List<Long> auth_ids) {
		if (auth_ids != null) {
			for (Long auth_id : auth_ids) {
				jdbcTemplate.update("INSERT INTO TB_RVA(ROLE_ID, AUTH_ID)VALUES(?, ?)", role_id, auth_id);
			}
		}
	}

	public void deleteUserRoles(Long role_id) {
		jdbcTemplate.update("DELETE FROM TB_UVR WHERE ROLE_ID = ?", role_id);
	}
}