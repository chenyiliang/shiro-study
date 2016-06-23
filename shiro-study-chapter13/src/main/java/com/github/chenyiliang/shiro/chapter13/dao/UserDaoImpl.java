package com.github.chenyiliang.shiro.chapter13.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.github.chenyiliang.shiro.chapter13.entity.User;

public class UserDaoImpl extends JdbcDaoSupport implements UserDao {

	@Override
	public User createUser(final User user) {
		final String sql = "INSERT INTO sys_users (username, password, salt, locked) VALUES (?,?,?,?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement psst = connection.prepareStatement(sql, new String[] { "id" });
				psst.setString(1, user.getUsername());
				psst.setString(2, user.getPassword());
				psst.setString(3, user.getSalt());
				psst.setBoolean(4, user.getLocked());
				return psst;
			}
		}, keyHolder);

		user.setId(keyHolder.getKey().longValue());
		return user;
	}

	@Override
	public void updateUser(User user) {
		String sql = "UPDATE sys_users SET username = ?, password = ?, salt = ?, locked = ? where id = ?";
		getJdbcTemplate().update(sql, user.getUsername(), user.getPassword(), user.getSalt(), user.getLocked(),
				user.getId());
	}

	@Override
	public void deleteUser(Long userId) {
		String sql = "DELETE FROM sys_users WHERE id = ?";
		getJdbcTemplate().update(sql, userId);
	}

	@Override
	public void correlationRoles(Long userId, Long... roleIds) {
		if (roleIds == null || roleIds.length == 0) {
			return;
		}
		String sql = "INSERT INTO sys_users_roles (user_id, role_id) VALUES (?,?)";
		for (Long roleId : roleIds) {
			if (!exists(userId, roleId)) {
				getJdbcTemplate().update(sql, userId, roleId);
			}
		}
	}

	@Override
	public void uncorrelationRoles(Long userId, Long... roleIds) {
		if (roleIds == null || roleIds.length == 0) {
			return;
		}
		String sql = "DELETE FROM sys_users_roles WHERE user_id = ? AND role_id = ?";
		for (Long roleId : roleIds) {
			if (exists(userId, roleId)) {
				getJdbcTemplate().update(sql, userId, roleId);
			}
		}
	}

	@Override
	public User findOne(Long userId) {
		String sql = "SELECT id, username, password, salt, locked FROM sys_users WHERE id = ?";
		List<User> userList = getJdbcTemplate().query(sql, new BeanPropertyRowMapper<User>(User.class), userId);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}

	@Override
	public User findByUsername(String username) {
		String sql = "SELECT id, username, password, salt, locked FROM sys_users WHERE username = ?";
		List<User> userList = getJdbcTemplate().query(sql, new BeanPropertyRowMapper<User>(User.class), username);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}

	@Override
	public Set<String> findRoles(String username) {
		String sql = "SELECT role FROM sys_users u, sys_roles r,sys_users_roles ur WHERE u.username=? AND u.id=ur.user_id AND r.id=ur.role_id";
		return new HashSet<String>(getJdbcTemplate().queryForList(sql, String.class, username));
	}

	@Override
	public Set<String> findPermissions(String username) {
		// 此处可以优化，比如查询到role后，一起获取roleId，然后直接根据roleId获取即可
		String sql = "SELECT permission FROM sys_users u, sys_roles r, sys_permissions p, sys_users_roles ur, sys_roles_permissions rp WHERE u.username=? AND u.id=ur.user_id AND r.id=ur.role_id AND r.id=rp.role_id AND p.id=rp.permission_id";
		return new HashSet<String>(getJdbcTemplate().queryForList(sql, String.class, username));
	}

	private boolean exists(Long userId, Long roleId) {
		String sql = "SELECT COUNT(1) FROM sys_users_roles WHERE user_id = ? AND role_id = ?";
		return getJdbcTemplate().queryForObject(sql, Integer.class, userId, roleId) != 0;
	}
}
