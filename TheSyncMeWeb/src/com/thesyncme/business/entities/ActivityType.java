package com.thesyncme.business.entities;

/**
 * Activity Type business entity class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class ActivityType {

	private String activityTypeId;
	private String description;
	public String getActivityTypeId() {
		return activityTypeId;
	}
	public void setActivityTypeId(String activityTypeId) {
		this.activityTypeId = activityTypeId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
}
