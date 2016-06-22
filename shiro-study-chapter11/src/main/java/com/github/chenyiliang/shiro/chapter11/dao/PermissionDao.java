package com.github.chenyiliang.shiro.chapter11.dao;

import com.github.chenyiliang.shiro.chapter11.entity.Permission;

public interface PermissionDao {
	Permission createPermission(Permission permission);

	void deletePermission(Long permissionId);
}
