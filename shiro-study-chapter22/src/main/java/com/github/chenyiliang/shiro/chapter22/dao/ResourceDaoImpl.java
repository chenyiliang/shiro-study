package com.github.chenyiliang.shiro.chapter22.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.github.chenyiliang.shiro.chapter22.entity.Resource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ResourceDaoImpl implements ResourceDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Resource createResource(final Resource resource) {
		final String sql = "INSERT INTO sys_resource (name, type, url, permission, parent_id, parent_ids, available) values (?,?,?,?,?,?,?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement psst = connection.prepareStatement(sql, new String[] { "id" });
				int count = 1;
				psst.setString(count++, resource.getName());
				psst.setString(count++, resource.getType().name());
				psst.setString(count++, resource.getUrl());
				psst.setString(count++, resource.getPermission());
				psst.setLong(count++, resource.getParentId());
				psst.setString(count++, resource.getParentIds());
				psst.setBoolean(count++, resource.getAvailable());
				return psst;
			}
		}, keyHolder);
		resource.setId(keyHolder.getKey().longValue());
		return resource;
	}

	@Override
	public Resource updateResource(Resource resource) {
		final String sql = "UPDATE sys_resource SET name = ?, type = ?, url = ?, permission = ?, parent_id = ?, parent_ids = ?, available = ? WHERE id = ?";
		jdbcTemplate.update(sql, resource.getName(), resource.getType().name(), resource.getUrl(),
				resource.getPermission(), resource.getParentId(), resource.getParentIds(), resource.getAvailable(),
				resource.getId());
		return resource;
	}

	public void deleteResource(Long resourceId) {
		Resource resource = findOne(resourceId);
		final String deleteSelfSql = "DELETE FROM sys_resource WHERE id = ?";
		jdbcTemplate.update(deleteSelfSql, resourceId);
		final String deleteDescendantsSql = "DELETE FROM sys_resource WHERE parent_ids LIKE ?";
		jdbcTemplate.update(deleteDescendantsSql, resource.makeSelfAsParentIds() + "%");
	}

	@Override
	public Resource findOne(Long resourceId) {
		final String sql = "SELECT id, name, type, url, permission, parent_id, parent_ids, available FROM sys_resource WHERE id = ?";
		List<Resource> resourceList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<Resource>(Resource.class),
				resourceId);
		if (resourceList.size() == 0) {
			return null;
		}
		return resourceList.get(0);
	}

	@Override
	public List<Resource> findAll() {
		final String sql = "SELECT id, name, type, url, permission, parent_id, parent_ids, available FROM sys_resource ORDER BY CONCAT(parent_ids, id) ASC";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Resource>(Resource.class));
	}

}
