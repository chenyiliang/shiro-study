package com.github.chenyiliang.shiro.chapter12.service;

import com.github.chenyiliang.shiro.chapter12.dao.PermissionDao;
import com.github.chenyiliang.shiro.chapter12.entity.Permission;

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
