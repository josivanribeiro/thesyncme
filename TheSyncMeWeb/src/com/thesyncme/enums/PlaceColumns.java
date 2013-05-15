package com.thesyncme.enums;

/**
 * Place columns enum.
 * 
 * @author Josivan Ribeiro
 *
 */
public enum PlaceColumns {
	ROLE_ROLE_ID ("role_roleId"),
	INFO_USERNAME ("username"), 
	INFO_EMAIL ("email"), 
	INFO_PASSWORD ("password"),
	INFO_TOKEN_OPERATION ("tokenOperation"),
	INFO_ABOUT ("about"),
	INFO_PHONE ("phone"),
	INFO_WEBSITE ("website"),
	LOCATION_LOCATION_ID ("location_locationId"),
	ADDRESS_ADDRESS_ID ("address_addressId"),
	RECOMMENDATION_USER_ID ("recommendation_userId"),
	COMMENT_USER_ID ("comment_userId"),
	COMMENT_TEXT ("comment_text"),
	NOTIFICATION_TYPE_ID ("notification_typeId"),
	FAN_REQUEST_USER_ID ("fanRequest_userId");
	
	private String columnName;
			
	private PlaceColumns (String columnName) {
		this.columnName = columnName;
	}
	
	public String getColumnName () {
		return columnName;
	}
}
