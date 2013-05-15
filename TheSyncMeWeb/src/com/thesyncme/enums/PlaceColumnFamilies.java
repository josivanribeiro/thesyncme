package com.thesyncme.enums;

/**
 * Place column families enum.
 * 
 * @author Josivan Ribeiro
 *
 */
public enum PlaceColumnFamilies {
	ROLE_ROLE_ID ("role_roleId"),
	INFO ("info"),
	LOCATION_LOCATION_ID ("location_locationId"),
	ADDRESS_ADDRESS_ID ("address_addressId"),
	RECOMMENDATION_USER_ID ("recommendation_userId"),
	COMMENT_USER_ID ("comment_userId"),
	COMMENT_TEXT ("comment_text"),
	NOTIFICATION_TYPE_ID ("notification_typeId"),
	FAN_REQUEST_USER_ID ("fanRequest_userId");
	
	private String columnFamilyName;
	
	private PlaceColumnFamilies (String columnFamilyName) {
		this.columnFamilyName = columnFamilyName;
	}
	public String getColumnFamilyName () {
		return columnFamilyName;
	}
	
}
