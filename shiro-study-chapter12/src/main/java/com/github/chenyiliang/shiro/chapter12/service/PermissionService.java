package com.github.chenyiliang.shiro.chapter12.service;

import com.github.chenyiliang.shiro.chapter12.entity.Permission;

public interface PermissionService {
	Permission createPermission(Permission permission);

	void deletePermission(Long permissionId);
}
