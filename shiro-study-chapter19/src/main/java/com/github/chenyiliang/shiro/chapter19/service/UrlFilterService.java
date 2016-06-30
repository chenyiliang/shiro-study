package com.github.chenyiliang.shiro.chapter19.service;

import java.util.List;

import com.github.chenyiliang.shiro.chapter19.entity.UrlFilter;

public interface UrlFilterService {

	UrlFilter createUrlFilter(UrlFilter urlFilter);

	UrlFilter updateUrlFilter(UrlFilter urlFilter);

	void deleteUrlFilter(Long urlFilterId);

	UrlFilter findOne(Long urlFilterId);

	List<UrlFilter> findAll();
}
