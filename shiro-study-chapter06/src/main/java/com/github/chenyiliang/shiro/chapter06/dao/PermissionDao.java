package com.github.chenyiliang.shiro.chapter06.dao;

import com.github.chenyiliang.shiro.chapter06.entity.Permission;

public interface PermissionDao {
	Permission createPermission(Permission permission);

	void deletePermission(Long permissionId);
}
