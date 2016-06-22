package com.github.chenyiliang.shiro.chapter11.service;

import com.github.chenyiliang.shiro.chapter11.entity.Permission;

public interface PermissionService {
	Permission createPermission(Permission permission);

	void deletePermission(Long permissionId);
}
