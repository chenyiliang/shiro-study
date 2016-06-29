package com.github.chenyiliang.shiro.chapter19.dao;

import java.util.List;

import com.github.chenyiliang.shiro.chapter19.entity.User;

public interface UserDao {

	User createUser(User user);

	User updateUser(User user);

	void deleteUser(Long userId);

	User findOne(Long userId);

	List<User> findAll();

	User findByUsername(String username);

}
