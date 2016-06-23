package com.github.chenyiliang.shiro.chapter12.dao;

import com.github.chenyiliang.shiro.chapter12.entity.Role;

public interface RoleDao {
	Role createRole(Role role);

	void deleteRole(Long roleId);

	void correlationPermissions(Long roleId, Long... permissionIds);

	void uncorrelationPermissions(Long roleId, Long... permissionIds);
}
