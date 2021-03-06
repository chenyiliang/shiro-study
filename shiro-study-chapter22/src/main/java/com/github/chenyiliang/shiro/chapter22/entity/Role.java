package com.github.chenyiliang.shiro.chapter22.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;// 编号
	private String role;// 角色标识 程序中判断使用,如"admin"
	private String description; // 角色描述,UI界面显示使用
	private List<Long> resourceIds;// 拥有的资源
	private Boolean available = Boolean.FALSE; // 是否可用,如果不可用将不会添加给用户

	public Role() {
	}

	public Role(String role, String description, Boolean available) {
		this.role = role;
		this.description = description;
		this.available = available;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Long> getResourceIds() {
		if (resourceIds == null) {
			resourceIds = new ArrayList<Long>();
		}
		return resourceIds;
	}

	public void setResourceIds(List<Long> resourceIds) {
		this.resourceIds = resourceIds;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public String getResourceIdsStr() {
		return String.join(",", getResourceIds().stream().map((id) -> id.toString()).collect(Collectors.toList()));
	}

	public void setResourceIdsStr(String resourceIdsStr) {
		if (resourceIdsStr != null && !resourceIdsStr.trim().isEmpty()) {
			String[] resourceIdStrs = resourceIdsStr.trim().split(",");
			for (String resourceIdStr : resourceIdStrs) {
				if (!resourceIdStr.trim().isEmpty()) {
					getResourceIds().add(Long.valueOf(resourceIdStr));
				}
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Role) {
			Role r = (Role) obj;
			return id != null ? id.equals(r.id) : r.id == null;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", role=" + role + ", description=" + description + ", resourceIds=" + resourceIds
				+ ", available=" + available + "]";
	}

}
