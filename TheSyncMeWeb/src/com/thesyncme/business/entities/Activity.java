package com.thesyncme.business.entities;

import java.util.Date;
import java.util.List;

/**
 * Activity business entity class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class Activity {

	private BaseUser fromUser;
	private BaseUser toUser;
	private ActivityType activityType;
	private Date creationDate;
	private Advertisement advertisement;
	private List<Photo> photoList;
	private List<User> userWhoEnjoyedList;
			
	public BaseUser getFromUser() {
		return fromUser;
	}
	public void setFromUser(BaseUser fromUser) {
		this.fromUser = fromUser;
	}
	public BaseUser getToUser() {
		return toUser;
	}
	public void setToUser(BaseUser toUser) {
		this.toUser = toUser;
	}
	public ActivityType getActivityType() {
		return activityType;
	}
	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Advertisement getAdvertisement() {
		return advertisement;
	}
	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}
	public List<Photo> getPhotoList() {
		return photoList;
	}
	public void setPhotoList(List<Photo> photoList) {
		this.photoList = photoList;
	}
	public List<User> getUserWhoEnjoyedList() {
		return userWhoEnjoyedList;
	}
	public void setUserWhoEnjoyedList(List<User> userWhoEnjoyedList) {
		this.userWhoEnjoyedList = userWhoEnjoyedList;
	}	
}
