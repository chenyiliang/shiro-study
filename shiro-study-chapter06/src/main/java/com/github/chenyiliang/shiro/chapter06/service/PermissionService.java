package com.github.chenyiliang.shiro.chapter06.service;

import com.github.chenyiliang.shiro.chapter06.entity.Permission;

public interface PermissionService {
	Permission createPermission(Permission permission);

	void deletePermission(Long permissionId);
}
