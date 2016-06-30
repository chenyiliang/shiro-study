package com.github.chenyiliang.shiro.chapter19.dao;

import java.util.List;

import com.github.chenyiliang.shiro.chapter19.entity.UrlFilter;

public interface UrlFilterDao {

	UrlFilter createUrlFilter(UrlFilter urlFilter);

	UrlFilter updateUrlFilter(UrlFilter urlFilter);

	void deleteUrlFilter(Long urlFilterId);

	UrlFilter findOne(Long urlFilterId);

	List<UrlFilter> findAll();
}
