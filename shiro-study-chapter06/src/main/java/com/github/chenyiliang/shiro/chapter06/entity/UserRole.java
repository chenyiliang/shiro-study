package com.github.chenyiliang.shiro.chapter06.entity;

import java.io.Serializable;

public class UserRole implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long userId;
	private Long roleId;

	public UserRole() {
	}

	public UserRole(Long userId, Long roleId) {
		this.userId = userId;
		this.roleId = roleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof UserRole) {
			UserRole ur = (UserRole) obj;
			return (userId != null ? userId.equals(ur.roleId) : ur.userId == null)
					&& (roleId != null ? roleId.equals(ur.roleId) : ur.roleId == null);
		}
		return false;
	}

	@Override
	public int hashCode() {
		int result = userId != null ? userId.hashCode() : 0;
		result = 31 * result + (roleId != null ? roleId.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "UserRole [userId=" + userId + ", roleId=" + roleId + "]";
	}

}
