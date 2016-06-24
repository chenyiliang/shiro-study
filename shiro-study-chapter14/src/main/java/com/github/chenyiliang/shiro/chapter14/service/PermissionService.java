package com.github.chenyiliang.shiro.chapter14.service;

import com.github.chenyiliang.shiro.chapter14.entity.Permission;

public interface PermissionService {
	Permission createPermission(Permission permission);

	void deletePermission(Long permissionId);
}
