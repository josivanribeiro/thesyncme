package com.thesyncme.constants;

import java.util.Locale;

/**
 * This class defines the constants used by the TheSyncMe web application.
 *   
 * @author Josivan Ribeiro
 *
 */
public class Constants {
	
	/**
	 * Constant that defines the User table name.
	 */
	public static final String USER_TABLENAME = "User";
	
	/**
	 * Constant that defines the Place table name.
	 */
	public static final String PLACE_TABLENAME = "Place";
	/**
	 * Constant that defines the Role table name.
	 */
	public static final String ROLE_TABLENAME = "Role";	
	/**
	 * Constant that defines the Location table name. 
	 */
	public static final String LOCATION_TABLENAME = "Location";
	
	/**
	 * Constant that defines the Education table name. 
	 */
	public static final String EDUCATION_TABLENAME = "Education";
	
	/**
	 * Constant that defines the Company table name.
	 */
	public static final String COMPANY_TABLENAME = "Company";
	
	/**
	 * Constant that defines the Position table name.
	 */
	public static final String POSITION_TABLENAME = "Position";
	
	/**
	 * Constant that defines the Language table name.
	 */
	public static final String LANGUAGE_TABLENAME = "Language";
	
	/**
	 * Constant that defines the Brasil default locale.
	 */
	public static final Locale BRAZIL = new java.util.Locale("pt", "BR", "");
	
	/**
	 * Constant that defines the application resource bundle base name.
	 */
	public static final String RESOURCE_BUNDLE_BASE_NAME = "com.thesyncme.bundle.messages";
	
	/**
	 * Constant that defines the login signin incorrect fields bundle property.
	 */
	public static final String LOGIN_SIGNIN_VALIDATION_INCORRECT_FIELDS_PROPERTY = "login.signin.validation.incorrect.fields";
	
	/**
	 * Constant that defines the login signin business error property. 
	 */
	public static final String LOGIN_SIGNIN_BUSINESS_ERROR = "login.signin.user.signin.business.error";
	
	/**
	 * Constant that defines the login signup account type bundle property.
	 */
	public static final String LOGIN_SIGNUP_ACCOUNT_TYPE_PROPERTY = "login.signup.accountType";
	
	/**
	 * Constant that defines the login signup account type user bundle property.
	 */
	public static final String LOGIN_SIGNUP_ACCOUNT_TYPE_USER_PROPERTY = "login.signup.accountType.user";
	
	/**
	 * Constant that defines the login signup account type place bundle property.
	 */
	public static final String LOGIN_SIGNUP_ACCOUNT_TYPE_PLACE_PROPERTY = "login.signup.accountType.place";
	
	/**
	 * Constant that defines the login signup account created bundle property.
	 */
	public static final String LOGIN_SIGNUP_ACCOUNT_CREATED_PROPERTY = "login.signup.account.created";
	
	/**
	 * Constant that defines the login signup all fields empty bundle property.
	 */
	public static final String LOGIN_SIGNUP_VALIDATION_ALL_FIELDS_EMPTY_PROPERTY = "login.signup.validation.all.fields.empty";
	
	/**
	 * Constant that defines the login signup all email property bundle property.
	 */
	public static final String LOGIN_SIGNUP_VALIDATION_INCORRECT_EMAIL_PROPERTY = "login.signup.validation.incorrect.email";
	
	/**
	 * Constant that defines the login retrieve password validation empty field bundle property.
	 */
	public static final String LOGIN_RETRIEVE_PASSWORD_VALIDATION_FIELD_EMPTY_PROPERTY = "login.retrieve.password.validation.field.empty";
	
	/**
	 * Constant that defines the login signup business error property. 
	 */
	public static final String LOGIN_SIGNUP_BUSINESS_ERROR = "login.signup.user.signup.business.error";
	
	/**
	 * Constant that defines the date formatting at property.
	 */
	public static final String DATE_FORMATTING_AT_PROPERTY = "date.formatting.at";
	
	/**
	 * Constant that defines the date formatting from property.
	 */
	public static final String DATE_FORMATTING_FROM_PROPERTY = "date.formatting.from";
	
	/**
	 * Constant that defines the default account type value.
	 */
	public static final String USER_OR_PLACE_ACCOUNT_TYPE = "O";
	
	/**
	 * Constant that defines the User account type character.
	 */
	public static final String USER_ACCOUNT_TYPE = "U";
	
	/**
	 * Constant that defines the Place account type character.
	 */
	public static final String PLACE_ACCOUNT_TYPE = "P";
	
	/**
	 * Constant that defines the pt_BR language label.
	 */
	public static final String PT_BR_LANGUAGE = "Português (Brasil)";
	
	/**
	 * Constant that defines the en_US language label.
	 */
	public static final String EN_US_LANGUAGE = "English (US)";
	
	/**
	 * Constant that defines the User email cookie key.
	 */
	public static final String USER_EMAIL_AUTH_COOKIE = "TSM_EML_CK";
	
	/**
	 * Constant that defines the User password cookie key.
	 */
	public static final String USER_PASSWORD_AUTH_COOKIE = "TSM_PWD_CK";
	
	/**
	 * Constant that defines the SHA-1 hash function.
	 */
	public static final String SHA1_HASH_FUNCTION = "SHA-1";
	
	/**
	 * Constant that defines the default smtp host.
	 */
	public static final String DEFAULT_SMTP_HOST = "smtp.gmail.com";
	
	/**
	 * Constant that defines the default smtp authentication user.
	 */
	public static final String DEFAULT_SMTP_AUTH_USER = "wolverinecampinas@gmail.com";
	
	/**
	 * Constant that defines the default smtp authentication password.
	 */
	public static final String DEFAULT_SMTP_AUTH_PWD = "j37r15s3";
	
	/**
	 * Constant that defines the default smtp port.
	 */
	public static final int DEFAULT_SMTP_PORT = 465;
	
	/**
	 * Constant that defines the TheSyncMe default sender.
	 */
	public static final String DEFAULT_SENDER = "noreply@thesyncme.com";
	
	/**
	 * Constant that defines the default header mailer.
	 */
	public static final String DEFAULT_HEADER_MAILER = "X-Mailer";
	
	/**
	 * Constant that defines the subject of the Reset your password feature.
	 */
	public static final String RESET_YOUR_PASSWORD_SUBJECT = "Reset your TheSyncMe password";
	
	/**
	 * Constant that defines the Email regex.
	 */
	public static final String EMAIL_REGEX = "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$";
	
	/**
	 * Constant that defines the User login page.
	 */
	public static final String USER_LOGIN_PAGE = "login?faces-redirect=true";
	
	/**
	 * Constant that defines the User places home page.
	 */
	public static final String USER_PLACES_HOME_PAGE = "userPlacesHome?faces-redirect=true";
	
	/**
	 * Constant that defines the User friends home page.
	 */
	public static final String USER_FRIENDS_HOME_PAGE = "userFriendsHome?faces-redirect=true";
	
	/**
	 * Constant that defines the Place home page.
	 */
	public static final String PLACE_HOME_PAGE = "placeHome?faces-redirect=true";	
		
}
