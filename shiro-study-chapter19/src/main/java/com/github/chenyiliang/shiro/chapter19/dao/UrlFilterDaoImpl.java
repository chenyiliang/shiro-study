package com.github.chenyiliang.shiro.chapter19.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.github.chenyiliang.shiro.chapter19.entity.UrlFilter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UrlFilterDaoImpl implements UrlFilterDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public UrlFilter createUrlFilter(final UrlFilter urlFilter) {
		final String sql = "INSERT INTO sys_url_filter (name, url, roles, permissions) VALUES (?,?,?,?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement psst = connection.prepareStatement(sql, new String[] { "id" });
				int count = 1;
				psst.setString(count++, urlFilter.getName());
				psst.setString(count++, urlFilter.getUrl());
				psst.setString(count++, urlFilter.getRoles());
				psst.setString(count++, urlFilter.getPermissions());
				return psst;
			}
		}, keyHolder);
		urlFilter.setId(keyHolder.getKey().longValue());
		return urlFilter;
	}

	@Override
	public UrlFilter updateUrlFilter(UrlFilter urlFilter) {
		final String sql = "UPDATE sys_url_filter SET name = ?, url = ?, roles = ?, permissions = ? WHERE id = ?";
		jdbcTemplate.update(sql, urlFilter.getName(), urlFilter.getUrl(), urlFilter.getRoles(),
				urlFilter.getPermissions(), urlFilter.getId());
		return urlFilter;
	}

	public void deleteUrlFilter(Long urlFilterId) {
		final String sql = "DELETE FROM sys_url_filter WHERE id = ?";
		jdbcTemplate.update(sql, urlFilterId);
	}

	@Override
	public UrlFilter findOne(Long urlFilterId) {
		final String sql = "SELECT id, name, url, roles, permissions FROM sys_url_filter WHERE id = ?";
		List<UrlFilter> urlFilterList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<UrlFilter>(UrlFilter.class),
				urlFilterId);
		if (urlFilterList.size() == 0) {
			return null;
		}
		return urlFilterList.get(0);
	}

	@Override
	public List<UrlFilter> findAll() {
		final String sql = "SELECT id, name, url, roles, permissions FROM sys_url_filter";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<UrlFilter>(UrlFilter.class));
	}

}
