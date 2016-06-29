package com.github.chenyiliang.shiro.chapter18.service;

import java.util.List;

import com.github.chenyiliang.shiro.chapter18.entity.Organization;

public interface OrganizationService {

	Organization createOrganization(Organization organization);

	Organization updateOrganization(Organization organization);

	void deleteOrganization(Long organizationId);

	Organization findOne(Long organizationId);

	List<Organization> findAll();

	Object findAllWithExclude(Organization excludeOraganization);

	void move(Organization source, Organization target);
}
