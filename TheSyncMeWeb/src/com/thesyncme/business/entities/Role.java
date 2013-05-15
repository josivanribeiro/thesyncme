package com.thesyncme.business.entities;

/**
 * Role business entity class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class Role {

	private Integer roleId;
	private String name;
	
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
