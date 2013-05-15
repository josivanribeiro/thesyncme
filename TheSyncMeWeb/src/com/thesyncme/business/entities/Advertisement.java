package com.thesyncme.business.entities;


/**
 * Advertisement business entity class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class Advertisement {

	private String advertisementId;
	private String message;	
	
	public String getAdvertisementId() {
		return advertisementId;
	}
	public void setAdvertisementId(String advertisementId) {
		this.advertisementId = advertisementId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}		
}
