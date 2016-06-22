package com.github.chenyiliang.shiro.chapter11.service;

import com.github.chenyiliang.shiro.chapter11.dao.RoleDao;
import com.github.chenyiliang.shiro.chapter11.dao.RoleDaoImpl;
import com.github.chenyiliang.shiro.chapter11.entity.Role;

public class RoleServiceImpl implements RoleService {

	private RoleDao roleDao = new RoleDaoImpl();

	@Override
	public Role createRole(Role role) {
		return roleDao.createRole(role);
	}

	@Override
	public void deleteRole(Long roleId) {
		roleDao.deleteRole(roleId);
	}

	/**
	 * 添加角色-权限之间关系
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	@Override
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		roleDao.correlationPermissions(roleId, permissionIds);
	}

	/**
	 * 移除角色-权限之间关系
	 * 
	 * @param roleId
	 * @param permissionIds
	 */
	@Override
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		roleDao.uncorrelationPermissions(roleId, permissionIds);
	}

}
