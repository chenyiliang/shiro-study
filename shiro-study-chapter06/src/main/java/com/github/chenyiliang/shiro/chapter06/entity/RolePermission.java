package com.github.chenyiliang.shiro.chapter06.entity;

import java.io.Serializable;

public class RolePermission implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long roleId;
	private Long permissionId;

	public RolePermission() {
	}

	public RolePermission(Long roleId, Long permissionId) {
		this.roleId = roleId;
		this.permissionId = permissionId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getPermissionId() {
		return permissionId;
	}

	public void setPermissionId(Long permissionId) {
		this.permissionId = permissionId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof RolePermission) {
			RolePermission rp = (RolePermission) obj;
			return (roleId != null ? roleId.equals(rp.roleId) : rp.roleId == null)
					&& (permissionId != null ? permissionId.equals(rp.permissionId) : rp.permissionId == null);
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = roleId != null ? roleId.hashCode() : 0;
		result = 31 * result + (permissionId != null ? permissionId.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "RolePermission [roleId=" + roleId + ", permissionId=" + permissionId + "]";
	}

}
