package com.github.chenyiliang.shiro.chapter22.dao;

import java.util.List;

import com.github.chenyiliang.shiro.chapter22.entity.Resource;

public interface ResourceDao {

	Resource createResource(Resource resource);

	Resource updateResource(Resource resource);

	void deleteResource(Long resourceId);

	Resource findOne(Long resourceId);

	List<Resource> findAll();

}
