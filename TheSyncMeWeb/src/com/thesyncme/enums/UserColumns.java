package com.thesyncme.enums;

/**
 * User columns enum.
 * 
 * @author Josivan Ribeiro
 *
 */
public enum UserColumns {
	ROLE_ROLE_ID ("role_roleId"),
	INFO_USERNAME ("username"), 
	INFO_EMAIL ("email"), 
	INFO_PASSWORD ("password"),
	INFO_TOKEN_OPERATION ("tokenOperation"),
	COMPANY_COMPANY_ID ("company_companyId"),
	LOCATION_LOCATION_ID ("location_locationId"),
	EDUCATION_EDUCATION_ID ("education_educationId"),
	POSITION_POSITION_ID ("position_positionId"),
	LANGUAGE_LANGUAGE_ID ("language_languageId"),
	RECOMMENDATION_PLACE_ID ("recommendation_placeId"),
	COMMENT_PLACE_ID ("comment_placeId"),
	COMMENT_TEXT ("comment_text"),
	NOTIFICATION_TYPE_ID ("notification_typeId"),
	NOTIFICATION_USER_ID ("notification_userId"),
	FRIEND_REQUEST_USER_ID ("friendRequest_userId");
	
	private String columnName;
			
	private UserColumns (String columnName) {
		this.columnName = columnName;
	}
	
	public String getColumnName () {
		return columnName;
	}
	
}
