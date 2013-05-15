package com.thesyncme.business.entities;

import java.util.List;

/**
 * Base User business entity class.
 * 
 * @author Josivan Ribeiro
 *
 */
public abstract class BaseUser {

	private String username;
	private String email;
	private String password;
	private String tokenOperation;
	private List<Role> roleList;
	private Location location;
	private String profileImagePath;
	private String reducedProfileImagePath;
	private Integer numberRecommendations;
	private Integer numberComments;
	private Integer numberPhotos;
	private Integer numberNotifications;
	private Integer numberUpdates;
	private List<Activity> activityList;
	private List<Photo> photoList;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTokenOperation() {
		return tokenOperation;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public void setTokenOperation(String tokenOperation) {
		this.tokenOperation = tokenOperation;
	}
	public String getProfileImagePath() {
		return profileImagePath;
	}
	public void setProfileImagePath(String profileImagePath) {
		this.profileImagePath = profileImagePath;
	}
	public String getReducedProfileImagePath() {
		return reducedProfileImagePath;
	}
	public void setReducedProfileImagePath(String reducedProfileImagePath) {
		this.reducedProfileImagePath = reducedProfileImagePath;
	}
	public Integer getNumberRecommendations() {
		return numberRecommendations;
	}
	public void setNumberRecommendations(Integer numberRecommendations) {
		this.numberRecommendations = numberRecommendations;
	}
	public Integer getNumberComments() {
		return numberComments;
	}
	public void setNumberComments(Integer numberComments) {
		this.numberComments = numberComments;
	}
	public Integer getNumberPhotos() {
		return numberPhotos;
	}
	public void setNumberPhotos(Integer numberPhotos) {
		this.numberPhotos = numberPhotos;
	}
	public Integer getNumberNotifications() {
		return numberNotifications;
	}
	public void setNumberNotifications(Integer numberNotifications) {
		this.numberNotifications = numberNotifications;
	}
	public Integer getNumberUpdates() {
		return numberUpdates;
	}
	public void setNumberUpdates(Integer numberUpdates) {
		this.numberUpdates = numberUpdates;
	}
	public List<Activity> getActivityList() {
		return activityList;
	}
	public void setActivityList(List<Activity> activityList) {
		this.activityList = activityList;
	}
	public List<Photo> getPhotoList() {
		return photoList;
	}
	public void setPhotoList(List<Photo> photoList) {
		this.photoList = photoList;
	}	
}
