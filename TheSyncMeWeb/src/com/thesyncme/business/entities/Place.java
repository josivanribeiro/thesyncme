package com.thesyncme.business.entities;

import java.util.List;


/**
 * Place business entity class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class Place extends BaseUser {

	private String about;
	private List<String> phoneList;
	private String website;
	private Address address;
	private Integer fanRequestNumber;
	private List<User> fanList;
	
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public List<String> getPhoneList() {
		return phoneList;
	}
	public void setPhoneList(List<String> phoneList) {
		this.phoneList = phoneList;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public Integer getFanRequestNumber() {
		return fanRequestNumber;
	}
	public void setFanRequestNumber(Integer fanRequestNumber) {
		this.fanRequestNumber = fanRequestNumber;
	}
	public List<User> getFanList() {
		return fanList;
	}
	public void setFanList(List<User> fanList) {
		this.fanList = fanList;
	}		
}
