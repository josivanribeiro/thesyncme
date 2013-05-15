package com.thesyncme.business.entities;

/**
 * Position business entity class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class Position {

	private Long positionId;
	private String name;
	
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
}
