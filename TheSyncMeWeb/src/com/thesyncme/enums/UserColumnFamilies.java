package com.thesyncme.enums;

/**
 * User column families enum.
 * 
 * @author Josivan Ribeiro
 *
 */
public enum UserColumnFamilies {
	ROLE_ROLE_ID ("role_roleId"),
	INFO ("info"),
	COMPANY_COMPANY_ID ("company_companyId"),
	LOCATION_LOCATION_ID ("location_locationId"),
	EDUCATION_EDUCATION_ID ("education_educationId"),
	POSITION_POSITION_ID ("position_positionId"),
	LANGUAGE_LANGUAGE_ID ("language_languageId"),
	RECOMMENDATION_PLACE_ID ("recommendation_placeId"),
	COMMENT_PLACE_ID ("comment_placeId"),
	COMMENT_TEXT ("comment_text"),
	NOTIFICATION_TYPE_ID ("notification_typeId"),
	FRIEND_REQUEST_USER_ID ("friendRequest_userId");
	
	private String columnFamilyName;
	
	private UserColumnFamilies (String columnFamilyName) {
		this.columnFamilyName = columnFamilyName;
	}
	public String getColumnFamilyName () {
		return columnFamilyName;
	}	
}
