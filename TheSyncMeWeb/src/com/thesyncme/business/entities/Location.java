package com.thesyncme.business.entities;

/**
 * Location business entity class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class Location {

	private Long locationId;
	private String cityName;
	private String stateName;
	private String countryName;
	
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}	
	
}
