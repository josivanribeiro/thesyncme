package com.thesyncme.enums;

/**
 * Roles enum.
 * 
 * @author Josivan Ribeiro
 *
 */
public enum Roles {
	ROLE_ADMIN (1, "ROLE_ADMIN"),
	ROLE_USER (2, "ROLE_USER");
	
	private Integer roleId;
	private String roleName;
		
	Roles (Integer roleId, String roleName) {
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId (Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
