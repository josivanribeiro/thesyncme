package com.thesyncme.business.entities;

/**
 * Education business entity class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class Education {

	private Long educationId;
	private String institutionName;
	private EducationType educationType;
	
	public Long getEducationId() {
		return educationId;
	}
	public void setEducationId(Long educationId) {
		this.educationId = educationId;
	}
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	public EducationType getEducationType() {
		return educationType;
	}
	public void setEducationType(EducationType educationType) {
		this.educationType = educationType;
	}	
}
