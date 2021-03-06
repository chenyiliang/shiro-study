package com.github.chenyiliang.shiro.chapter19.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.chenyiliang.shiro.chapter19.dao.OrganizationDao;
import com.github.chenyiliang.shiro.chapter19.entity.Organization;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService {
	@Autowired
	private OrganizationDao organizationDao;

	@Override
	public Organization createOrganization(Organization organization) {
		return organizationDao.createOrganization(organization);
	}

	@Override
	public Organization updateOrganization(Organization organization) {
		return organizationDao.updateOrganization(organization);
	}

	@Override
	public void deleteOrganization(Long organizationId) {
		organizationDao.deleteOrganization(organizationId);
	}

	@Override
	public Organization findOne(Long organizationId) {
		return organizationDao.findOne(organizationId);
	}

	@Override
	public List<Organization> findAll() {
		return organizationDao.findAll();
	}

	@Override
	public List<Organization> findAllWithExclude(Organization excludeOraganization) {
		return organizationDao.findAllWithExclude(excludeOraganization);
	}

	@Override
	public void move(Organization source, Organization target) {
		organizationDao.move(source, target);
	}
}
