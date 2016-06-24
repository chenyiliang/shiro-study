package com.github.chenyiliang.shiro.chapter14.dao;

import com.github.chenyiliang.shiro.chapter14.entity.Permission;

public interface PermissionDao {
	Permission createPermission(Permission permission);

	void deletePermission(Long permissionId);
}
