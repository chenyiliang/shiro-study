package com.github.chenyiliang.shiro.chapter18.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.github.chenyiliang.shiro.chapter18.entity.Organization;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class OrganizationDaoImpl implements OrganizationDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Organization createOrganization(final Organization organization) {
		final String sql = "INSERT INTO sys_organization (name, parent_id, parent_ids, available) VALUES (?,?,?,?)";

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement psst = connection.prepareStatement(sql, new String[] { "id" });
				int count = 1;
				psst.setString(count++, organization.getName());
				psst.setLong(count++, organization.getParentId());
				psst.setString(count++, organization.getParentIds());
				psst.setBoolean(count++, organization.getAvailable());
				return psst;
			}
		}, keyHolder);
		organization.setId(keyHolder.getKey().longValue());
		return organization;
	}

	@Override
	public Organization updateOrganization(Organization organization) {
		final String sql = "UPDATE sys_organization SET name = ?, parent_id = ?, parent_ids = ?, available = ? WHERE id = ?";
		jdbcTemplate.update(sql, organization.getName(), organization.getParentId(), organization.getParentIds(),
				organization.getAvailable(), organization.getId());
		return organization;
	}

	public void deleteOrganization(Long organizationId) {
		Organization organization = findOne(organizationId);
		final String deleteSelfSql = "DELETE FROM sys_organization WHERE id = ?";
		jdbcTemplate.update(deleteSelfSql, organizationId);
		final String deleteDescendantsSql = "DELETE FROM sys_organization WHERE parent_ids LIKE ?";
		jdbcTemplate.update(deleteDescendantsSql, organization.makeSelfAsParentIds() + "%");
	}

	@Override
	public Organization findOne(Long organizationId) {
		final String sql = "SELECT id, name, parent_id, parent_ids, available FROM sys_organization WHERE id = ?";
		List<Organization> organizationList = jdbcTemplate.query(sql,
				new BeanPropertyRowMapper<Organization>(Organization.class), organizationId);
		if (organizationList.size() == 0) {
			return null;
		}
		return organizationList.get(0);
	}

	@Override
	public List<Organization> findAll() {
		final String sql = "SELECT id, name, parent_id, parent_ids, available FROM sys_organization";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Organization>(Organization.class));
	}

	@Override
	public List<Organization> findAllWithExclude(Organization excludeOraganization) {
		// TODO 改成not exists 利用索引
		final String sql = "SELECT id, name, parent_id, parent_ids, available FROM sys_organization WHERE id != ? AND parent_ids NOT LIKE ?";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Organization>(Organization.class),
				excludeOraganization.getId(), excludeOraganization.makeSelfAsParentIds() + "%");
	}

	@Override
	public void move(Organization source, Organization target) {
		String moveSourceSql = "UPDATE sys_organization SET parent_id = ?, parent_ids = ? WHERE id = ?";
		jdbcTemplate.update(moveSourceSql, target.getId(), target.getParentIds(), source.getId());
		String moveSourceDescendantsSql = "UPDATE sys_organization SET parent_ids = concat(?, substring(parent_ids, length(?))) WHERE parent_ids LIKE ?";
		jdbcTemplate.update(moveSourceDescendantsSql, target.makeSelfAsParentIds(), source.makeSelfAsParentIds(),
				source.makeSelfAsParentIds() + "%");
	}
}
