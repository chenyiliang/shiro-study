package com.github.chenyiliang.shiro.chapter19.service;

import java.util.List;
import java.util.Set;

import com.github.chenyiliang.shiro.chapter19.entity.User;

public interface UserService {

	/**
	 * 创建用户
	 * 
	 * @param user
	 */
	User createUser(User user);

	User updateUser(User user);

	void deleteUser(Long userId);

	/**
	 * 修改密码
	 * 
	 * @param userId
	 * @param newPassword
	 */
	void changePassword(Long userId, String newPassword);

	User findOne(Long userId);

	List<User> findAll();

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
