package com.github.chenyiliang.shiro.chapter18.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.github.chenyiliang.shiro.chapter18.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Role createRole(final Role role) {
		final String sql = "INSERT INTO sys_role (role, description, resource_ids, available) VALUES (?,?,?,?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement psst = connection.prepareStatement(sql, new String[] { "id" });
				int count = 1;
				psst.setString(count++, role.getRole());
				psst.setString(count++, role.getDescription());
				psst.setString(count++, role.getResourceIdsStr());
				psst.setBoolean(count++, role.getAvailable());
				return psst;
			}
		}, keyHolder);
		role.setId(keyHolder.getKey().longValue());
		return role;
	}

	@Override
	public Role updateRole(Role role) {
		final String sql = "UPDATE sys_role SET role = ?, description = ?, resource_ids = ?, available = ? WHERE id = ?";
		jdbcTemplate.update(sql, role.getRole(), role.getDescription(), role.getResourceIdsStr(), role.getAvailable(),
				role.getId());
		return role;
	}

	public void deleteRole(Long roleId) {
		final String sql = "DELETE FROM sys_role WHERE id = ?";
		jdbcTemplate.update(sql, roleId);
	}

	@Override
	public Role findOne(Long roleId) {
		final String sql = "SELECT id, role, description, resource_ids as resourceIdsStr, available FROM sys_role WHERE id = ?";
		List<Role> roleList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Role>(Role.class), roleId);
		if (roleList.size() == 0) {
			return null;
		}
		return roleList.get(0);
	}

	@Override
	public List<Role> findAll() {
		final String sql = "SELECT id, role, description, resource_ids as resourceIdsStr, available FROM sys_role";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Role>(Role.class));
	}

}
