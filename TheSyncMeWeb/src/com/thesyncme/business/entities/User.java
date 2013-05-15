package com.thesyncme.business.entities;

import java.util.List;

/**
 * User business entity class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class User extends BaseUser {
	
	private Company company;
	private List<Education> educationList;
	private Position position;
	private List<Language> languageList;
	private Place currentPlace;
	private List<Place> placeList;
	private Integer numberFriendRequests;
	private List<User> friendList;
	private List<Comment> commentList;
	
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public List<Education> getEducationList() {
		return educationList;
	}
	public void setEducationList(List<Education> educationList) {
		this.educationList = educationList;
	}
	public List<Language> getLanguageList() {
		return languageList;
	}
	public void setLanguageList(List<Language> languageList) {
		this.languageList = languageList;
	}
	public Place getCurrentPlace() {
		return currentPlace;
	}
	public void setCurrentPlace(Place currentPlace) {
		this.currentPlace = currentPlace;
	}
	public List<Place> getPlaceList() {
		return placeList;
	}
	public void setPlaceList(List<Place> placeList) {
		this.placeList = placeList;
	}
	public Integer getNumberFriendRequests() {
		return numberFriendRequests;
	}
	public void setNumberFriendRequests(Integer numberFriendRequests) {
		this.numberFriendRequests = numberFriendRequests;
	}
	public List<User> getFriendList() {
		return friendList;
	}	
	public void setFriendList(List<User> friendList) {
		this.friendList = friendList;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}	
}
