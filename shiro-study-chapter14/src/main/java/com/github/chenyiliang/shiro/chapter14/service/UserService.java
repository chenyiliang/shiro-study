package com.github.chenyiliang.shiro.chapter14.service;

import java.util.Set;

import com.github.chenyiliang.shiro.chapter14.entity.User;

public interface UserService {
	/**
	 * 创建用户
	 * 
	 * @param user
	 */
	User createUser(User user);

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param newPassword
	 */
	void changePassword(Long userId, String newPassword);

	/**
	 * 添加用户-角色关系
	 * 
	 * @param userId
	 * @param roleIds
	 */
	void correlationRoles(Long userId, Long... roleIds);

	/**
	 * 移除用户-角色关系
	 * 
	 * @param userId
	 * @param roleIds
	 */
	void uncorrelationRoles(Long userId, Long... roleIds);

	/**
	 * 根据用户名查找用户
	 * 
	 * @param username
	 * @return
	 */
	User findByUsername(String username);

	/**
	 * 根据用户名查找其角色
	 * 
	 * @param username
	 * @return
	 */
	Set<String> findRoles(String username);

	/**
	 * 根据用户名查找其权限
	 * 
	 * @param username
	 * @return
	 */
	Set<String> findPermissions(String username);
}
