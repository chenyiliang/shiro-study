package com.github.chenyiliang.shiro.chapter22.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.github.chenyiliang.shiro.chapter22.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public User createUser(final User user) {
		final String sql = "INSERT INTO sys_user (organization_id, username, password, salt, role_ids, locked) VALUES (?,?,?,?,?,?)";
		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement psst = connection.prepareStatement(sql, new String[] { "id" });
				int count = 1;
				psst.setLong(count++, user.getOrganizationId());
				psst.setString(count++, user.getUsername());
				psst.setString(count++, user.getPassword());
				psst.setString(count++, user.getSalt());
				psst.setString(count++, user.getRoleIdsStr());
				psst.setBoolean(count++, user.getLocked());
				return psst;
			}
		}, keyHolder);

		user.setId(keyHolder.getKey().longValue());
		return user;
	}

	public User updateUser(User user) {
		String sql = "UPDATE sys_user SET organization_id = ?, username = ?, password = ?, salt = ?, role_ids = ?, locked = ? WHERE id = ?";
		jdbcTemplate.update(sql, user.getOrganizationId(), user.getUsername(), user.getPassword(), user.getSalt(),
				user.getRoleIdsStr(), user.getLocked(), user.getId());
		return user;
	}

	public void deleteUser(Long userId) {
		String sql = "DELETE FROM sys_user WHERE id = ?";
		jdbcTemplate.update(sql, userId);
	}

	@Override
	public User findOne(Long userId) {
		String sql = "SELECT id, organization_id, username, password, salt, role_ids as roleIdsStr, locked FROM sys_user WHERE id = ?";
		List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), userId);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}

	@Override
	public List<User> findAll() {
		String sql = "SELECT id, organization_id, username, password, salt, role_ids as roleIdsStr, locked FROM sys_user";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class));
	}

	@Override
	public User findByUsername(String username) {
		String sql = "SELECT id, organization_id, username, password, salt, role_ids as roleIdsStr, locked FROM sys_user WHERE username = ?";
		List<User> userList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), username);
		if (userList.size() == 0) {
			return null;
		}
		return userList.get(0);
	}
}
