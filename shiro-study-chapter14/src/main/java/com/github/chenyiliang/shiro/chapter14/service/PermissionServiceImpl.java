package com.github.chenyiliang.shiro.chapter14.service;

import com.github.chenyiliang.shiro.chapter14.dao.PermissionDao;
import com.github.chenyiliang.shiro.chapter14.entity.Permission;

public class PermissionServiceImpl implements PermissionService {

	private PermissionDao permissionDao;

	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}

	@Override
	public Permission createPermission(Permission permission) {
		return permissionDao.createPermission(permission);
	}

	@Override
	public void deletePermission(Long permissionId) {
		permissionDao.deletePermission(permissionId);
	}

}
