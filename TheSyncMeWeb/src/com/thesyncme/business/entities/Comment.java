package com.thesyncme.business.entities;

import java.util.Date;

/**
 * Comment business entity class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class Comment {

	private Long commentId;
	private BaseUser fromUser;
	private BaseUser toPlace;
	private String comment;
	private Date creationDate;
	
	public Long getCommentId() {
		return commentId;
	}
	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	public BaseUser getFromUser() {
		return fromUser;
	}
	public void setFromUser(BaseUser fromUser) {
		this.fromUser = fromUser;
	}
	public BaseUser getToPlace() {
		return toPlace;
	}
	public void setToPlace(BaseUser toPlace) {
		this.toPlace = toPlace;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}	
}
