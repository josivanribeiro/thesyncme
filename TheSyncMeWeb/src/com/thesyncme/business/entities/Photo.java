package com.thesyncme.business.entities;

import java.util.List;

/**
 * Photo business entity class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class Photo {

	private String photoId;
	private String name;
	private String filePath;
	private List<User> userWhoEnjoyedList;
	
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public List<User> getUserWhoEnjoyedList() {
		return userWhoEnjoyedList;
	}
	public void setUserWhoEnjoyedList(List<User> userWhoEnjoyedList) {
		this.userWhoEnjoyedList = userWhoEnjoyedList;
	}	
}
