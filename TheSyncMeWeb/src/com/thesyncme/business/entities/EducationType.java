package com.thesyncme.business.entities;

/**
 * Education Type business entity enum.
 * 
 * @author Josivan Ribeiro
 *
 */
public enum EducationType {

	SCHOOL("School"),COLLEGE("College");
	
	private String name;
	
	private EducationType (String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}	
	
}
