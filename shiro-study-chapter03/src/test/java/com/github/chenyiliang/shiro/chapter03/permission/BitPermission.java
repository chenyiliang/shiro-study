package com.github.chenyiliang.shiro.chapter03.permission;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.util.StringUtils;

public class BitPermission implements Permission {
	private String resourceIdentify;
	private int permissionBit;
	private String instanceId;

	public BitPermission(String permissionString) {
		String[] array = permissionString.split("\\+");

		if (array.length > 1) {
			this.resourceIdentify = array[1];
		}

		if (!StringUtils.hasText(this.resourceIdentify)) {
			this.resourceIdentify = "*";
		}

		if (array.length > 2) {
			this.permissionBit = Integer.valueOf(array[2]);
		}

		if (array.length > 3) {
			this.instanceId = array[3];
		}

		if (!StringUtils.hasText(this.instanceId)) {
			this.instanceId = "*";
		}
	}

	@Override
	public boolean implies(Permission p) {
		if (!(p instanceof BitPermission)) {
			return false;
		}

		BitPermission other = (BitPermission) p;

		if (!("*".equals(this.resourceIdentify) || this.resourceIdentify.equals(other.resourceIdentify))) {
			return false;
		}

		if (!(this.permissionBit == 0 || (this.permissionBit & other.permissionBit) != 0)) {
			return false;
		}

		if (!("*".equals(this.instanceId) || this.instanceId.equals(other.instanceId))) {
			return false;
		}

		return true;
	}

	@Override
	public String toString() {
		return "BitPermission [resourceIdentify=" + resourceIdentify + ", permissionBit=" + permissionBit
				+ ", instanceId=" + instanceId + "]";
	}

}
