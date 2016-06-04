package com.github.chenyiliang.shiro.chapter06.service;

import com.github.chenyiliang.shiro.chapter06.dao.PermissionDao;
import com.github.chenyiliang.shiro.chapter06.dao.PermissionDaoImpl;
import com.github.chenyiliang.shiro.chapter06.entity.Permission;

public class PermissionServiceImpl implements PermissionService {

	private PermissionDao permissionDao = new PermissionDaoImpl();

	@Override
	public Permission createPermission(Permission permission) {
		return permissionDao.createPermission(permission);
	}

	@Override
	public void deletePermission(Long permissionId) {
		permissionDao.deletePermission(permissionId);
	}

}
