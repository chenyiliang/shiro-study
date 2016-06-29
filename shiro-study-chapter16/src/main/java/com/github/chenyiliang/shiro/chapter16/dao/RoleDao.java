package com.github.chenyiliang.shiro.chapter16.dao;

import java.util.List;

import com.github.chenyiliang.shiro.chapter16.entity.Role;

public interface RoleDao {

	Role createRole(Role role);

	Role updateRole(Role role);

	void deleteRole(Long roleId);

	Role findOne(Long roleId);

	List<Role> findAll();
}
