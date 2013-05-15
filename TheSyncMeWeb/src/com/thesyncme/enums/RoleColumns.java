package com.thesyncme.enums;

/**
 * Role enum columns.
 * 
 * @author Josivan Ribeiro
 *
 */
public enum RoleColumns {
	ROLE_ID ("roleId"),
	NAME ("name");
					
	private String columnName;
			
	private RoleColumns (String columnName) {
		this.columnName = columnName;
	}
	
	public String getColumnName () {
		return columnName;
	}	
}
