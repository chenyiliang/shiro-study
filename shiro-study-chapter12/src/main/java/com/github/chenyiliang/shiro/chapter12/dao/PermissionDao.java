package com.github.chenyiliang.shiro.chapter12.dao;

import com.github.chenyiliang.shiro.chapter12.entity.Permission;

public interface PermissionDao {
	Permission createPermission(Permission permission);

	void deletePermission(Long permissionId);
}
