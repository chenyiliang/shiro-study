package com.github.chenyiliang.shiro.chapter16.entity;

import java.io.Serializable;

public class Organization implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;// 编号
	private String name;// 组织机构名称
	private Long parentId;// 父编号
	private String parentIds; // 父编号列表，如1/2/
	private Boolean available = Boolean.FALSE;

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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public Boolean getAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public boolean isRootNode() {
		return parentId == 0;
	}

	public String makeSelfAsParentIds() {
		return getParentIds() + getId() + "/";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Organization) {
			Organization o = (Organization) obj;
			return id != null ? id.equals(o.id) : o.id == null;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + ", parentId=" + parentId + ", parentIds=" + parentIds
				+ ", available=" + available + "]";
	}

}
