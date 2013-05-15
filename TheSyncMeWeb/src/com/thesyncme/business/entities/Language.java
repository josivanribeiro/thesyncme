package com.thesyncme.business.entities;

/**
 * Language business entity class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class Language {

	private Integer languageId;
	private String name;
	
	public Integer getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	
}
