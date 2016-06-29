package com.github.chenyiliang.shiro.chapter16.entity;

import java.io.Serializable;

public class Resource implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;// 编号
	private String name;// 资源名称
	private ResourceType type = ResourceType.menu;// 资源类型
	private String url;// 资源路径
	private String permission;// 权限字符串
	private Long parentId;// 父编号
	private String parentIds;// 父编号列表
	private Boolean available = Boolean.FALSE;

	public static enum ResourceType {
		menu("菜单"), button("按钮");
		private final String info;

		private ResourceType(String info) {
			this.info = info;
		}

		public String getInfo() {
			return info;
		}
	}

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

	public ResourceType getType() {
		return type;
	}

	public void setType(ResourceType type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
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
		if (obj instanceof Resource) {
			Resource r = (Resource) obj;
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
		return "Resource [id=" + id + ", name=" + name + ", type=" + type + ", url=" + url + ", permission="
				+ permission + ", parentId=" + parentId + ", parentIds=" + parentIds + ", available=" + available + "]";
	}

}
