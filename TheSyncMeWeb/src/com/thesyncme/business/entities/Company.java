package com.thesyncme.business.entities;

/**
 * Company business entity class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class Company {

	private Long companyId;
	private String name;
	
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId (Long companyId) {
		this.companyId = companyId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
