package com.github.chenyiliang.shiro.chapter13.dao;

import com.github.chenyiliang.shiro.chapter13.entity.Permission;

public interface PermissionDao {
	Permission createPermission(Permission permission);

	void deletePermission(Long permissionId);
}
