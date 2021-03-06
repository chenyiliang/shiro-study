package com.github.chenyiliang.shiro.chapter18.service;

import java.util.List;
import java.util.Set;

import com.github.chenyiliang.shiro.chapter18.entity.Role;

public interface RoleService {

	Role createRole(Role role);

	Role updateRole(Role role);

	void deleteRole(Long roleId);

	Role findOne(Long roleId);

	List<Role> findAll();

	/**
	 * 根据角色编号得到角色标识符列表
	 * 
	 * @param roleIds
	 * @return
	 */
	Set<String> findRoles(Long... roleIds);

	/**
	 * 根据角色编号得到权限字符串列表
	 * 
	 * @param roleIds
	 * @return
	 */
	Set<String> findPermissions(Long[] roleIds);
}
