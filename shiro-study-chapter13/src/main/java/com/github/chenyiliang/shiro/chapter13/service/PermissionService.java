package com.github.chenyiliang.shiro.chapter13.service;

import com.github.chenyiliang.shiro.chapter13.entity.Permission;

public interface PermissionService {
	Permission createPermission(Permission permission);

	void deletePermission(Long permissionId);
}
