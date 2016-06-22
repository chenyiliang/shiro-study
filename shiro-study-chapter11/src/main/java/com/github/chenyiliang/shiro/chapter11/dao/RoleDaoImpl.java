package com.github.chenyiliang.shiro.chapter11.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.github.chenyiliang.shiro.chapter11.JdbcTemplateUtils;
import com.github.chenyiliang.shiro.chapter11.entity.Role;


public class RoleDaoImpl implements RoleDao {
	private JdbcTemplate jdbcTemplate = JdbcTemplateUtils.jdbcTemplate();

	@Override
	public Role createRole(final Role Role) {
		final String sql = "INSERT INTO sys_roles (role, description, available) VALUES (?,?,?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement psst = connection.prepareStatement(sql, new String[] { "id" });
				psst.setString(1, Role.getRole());
				psst.setString(2, Role.getDescription());
				psst.setBoolean(3, Role.getAvailable());
				return psst;
			}
		}, keyHolder);
		Role.setId(keyHolder.getKey().longValue());

		return Role;
	}

	@Override
	public void deleteRole(Long roleId) {
		// 首先把和role关联的相关表数据删掉
		String sql = "DELETE FROM sys_users_roles WHERE role_id = ?";
		jdbcTemplate.update(sql, roleId);

		sql = "DELETE FROM sys_roles WHERE id = ?";
		jdbcTemplate.update(sql, roleId);
	}

	@Override
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		if (permissionIds == null || permissionIds.length == 0) {
			return;
		}
		String sql = "INSERT INTO sys_roles_permissions (role_id, permission_id) values (?,?)";
		for (Long permissionId : permissionIds) {
			if (!exists(roleId, permissionId)) {
				jdbcTemplate.update(sql, roleId, permissionId);
			}
		}
	}

	@Override
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		if (permissionIds == null || permissionIds.length == 0) {
			return;
		}
		String sql = "DELETE FROM sys_roles_permissions WHERE role_id = ? AND permission_id = ?";
		for (Long permissionId : permissionIds) {
			if (exists(roleId, permissionId)) {
				jdbcTemplate.update(sql, roleId, permissionId);
			}
		}
	}

	private boolean exists(Long roleId, Long permissionId) {
		String sql = "SELECT COUNT(1) FROM sys_roles_permissions WHERE role_id = ? AND permission_id = ?";
		return jdbcTemplate.queryForObject(sql, Integer.class, roleId, permissionId) != 0;
	}
}
