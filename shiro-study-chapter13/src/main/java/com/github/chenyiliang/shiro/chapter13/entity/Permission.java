package com.github.chenyiliang.shiro.chapter13.entity;

import java.io.Serializable;

public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String permission;// 权限标识 程序中判断使用,如"user:create"
	private String description;// 权限描述,UI界面显示使用
	private Boolean available = Boolean.FALSE;// 是否可用,如果不可用将不会添加给角色

	public Permission() {
	}

	public Permission(String permission, String description, Boolean available) {
		this.permission = permission;
		this.description = description;
		this.available = available;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Permission) {
			Permission p = (Permission) obj;
			return id != null ? id.equals(p.id) : p.id == null;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", permission=" + permission + ", description=" + description + ", available="
				+ available + "]";
	}

}
