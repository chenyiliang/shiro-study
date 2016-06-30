package com.github.chenyiliang.shiro.chapter19.entity;

import java.io.Serializable;

public class UrlFilter implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;// url名称/描述
	private String url;// 地址
	private String roles;// 所需要的角色，可省略
	private String permissions;// 所需要的权限，可省略

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof UrlFilter) {
			UrlFilter urlFilter = (UrlFilter) obj;
			return id != null ? id.equals(urlFilter.id) : urlFilter.id == null;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "UrlFilter [id=" + id + ", name=" + name + ", url=" + url + ", roles=" + roles + ", permissions="
				+ permissions + "]";
	}

}
