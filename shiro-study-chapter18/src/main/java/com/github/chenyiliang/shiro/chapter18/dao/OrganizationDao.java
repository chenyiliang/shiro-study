package com.github.chenyiliang.shiro.chapter18.dao;

import java.util.List;

import com.github.chenyiliang.shiro.chapter18.entity.Organization;

public interface OrganizationDao {

	Organization createOrganization(Organization organization);

	Organization updateOrganization(Organization organization);

	void deleteOrganization(Long organizationId);

	Organization findOne(Long organizationId);

	List<Organization> findAll();

	List<Organization> findAllWithExclude(Organization excludeOraganization);

	void move(Organization source, Organization target);
}
