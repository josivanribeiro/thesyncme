package com.thesyncme.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jasypt.digest.StandardStringDigester;
import org.jasypt.springsecurity3.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.thesyncme.business.entities.Education;
import com.thesyncme.business.entities.Language;
import com.thesyncme.business.entities.Role;
import com.thesyncme.business.entities.User;
import com.thesyncme.constants.Constants;
import com.thesyncme.dao.AbstractDAO;
import com.thesyncme.dao.UserDAO;
import com.thesyncme.enums.UserColumnFamilies;
import com.thesyncme.enums.UserColumns;
import com.thesyncme.exceptions.DataAccessException;
import com.thesyncme.helper.Helper;
import com.thesyncme.security.SecureRandomNumber;

@Repository
public class UserDAOImpl extends AbstractDAO implements UserDAO {
	/**
	 * Default logger.
	 */
	private static Logger logger = Logger.getLogger (UserDAOImpl.class);
	/**
	 * Attribute that defines the default password encoder.
	 */
	private PasswordEncoder passwordEncoder;
		
	public void setPasswordEncoder (PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void create (User user) throws DataAccessException {
		logger.info ("Start executing the method create.");
		try {			
			// encrypting the user password
			String digestedPassword = passwordEncoder.encodePassword (user.getPassword(), null);
			String[] columnNameArr = {
				UserColumns.INFO_USERNAME.getColumnName (),
				UserColumns.INFO_EMAIL.getColumnName (),
				UserColumns.INFO_PASSWORD.getColumnName ()
			};
			String[] dataArr = {
				user.getUsername (), user.getEmail (), digestedPassword
			};
			String rowKey = user.getUsername ();
			addRecordWithSingleColumnFamily (Constants.USER_TABLENAME, rowKey, UserColumnFamilies.INFO.getColumnFamilyName (), columnNameArr, dataArr);
			// updating the user roles
			for (Role role : user.getRoleList()) {
				String roleId = role.getRoleId ().toString ();
				String roleIdRowKey = UserColumns.ROLE_ROLE_ID.getColumnName() + "_" + Helper.getCurrentTimestamp ();
				addColumnEntry (Constants.USER_TABLENAME, UserColumnFamilies.ROLE_ROLE_ID.getColumnFamilyName (), rowKey, roleIdRowKey, roleId);
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred during the user creation.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method create.");
	}
	
	@Override
	public void update (User user) throws DataAccessException {
		logger.info ("Start executing the method update.");
		String rowKey = user.getUsername ();
		try {
			// updating the company
			String companyId = user.getCompany().getCompanyId().toString ();
			addColumnEntry (Constants.USER_TABLENAME, UserColumnFamilies.COMPANY_COMPANY_ID.getColumnFamilyName(), rowKey, UserColumns.COMPANY_COMPANY_ID.getColumnName(), companyId);			
			// updating the location
			String locationId = user.getLocation().getLocationId().toString ();
			addColumnEntry (Constants.USER_TABLENAME, UserColumnFamilies.LOCATION_LOCATION_ID.getColumnFamilyName(), rowKey, UserColumns.LOCATION_LOCATION_ID.getColumnName(), locationId);			
			// updating the position
			String positionId = user.getPosition().getPositionId().toString ();
			addColumnEntry (Constants.USER_TABLENAME, UserColumnFamilies.POSITION_POSITION_ID.getColumnFamilyName(), rowKey, UserColumns.POSITION_POSITION_ID.getColumnName(), positionId);			
			// updating the user education
				// removing the old entries for the user education before updating it
			removeColumnEntries (Constants.USER_TABLENAME, rowKey, UserColumnFamilies.EDUCATION_EDUCATION_ID.getColumnFamilyName(), UserColumns.EDUCATION_EDUCATION_ID.getColumnName());
			for (Education education : user.getEducationList()) {
				String educationId = education.getEducationId().toString();
				String educationIdRowKey = UserColumns.EDUCATION_EDUCATION_ID.getColumnName() + "_" + Helper.getCurrentTimestamp();
				addColumnEntry (Constants.USER_TABLENAME, UserColumnFamilies.EDUCATION_EDUCATION_ID.getColumnFamilyName(), rowKey, educationIdRowKey, educationId);
			}
			// updating the user languages
				// removing the old entries for the user languages before updating them
			removeColumnEntries (Constants.USER_TABLENAME, rowKey, UserColumnFamilies.LANGUAGE_LANGUAGE_ID.getColumnFamilyName(), UserColumns.LANGUAGE_LANGUAGE_ID.getColumnName());
			for (Language language : user.getLanguageList()) {
				String languageId = language.getLanguageId().toString();
				String languageIdRowKey = UserColumns.LANGUAGE_LANGUAGE_ID.getColumnName() + "_" + Helper.getCurrentTimestamp();
				addColumnEntry (Constants.USER_TABLENAME, UserColumnFamilies.LANGUAGE_LANGUAGE_ID.getColumnFamilyName(), rowKey, languageIdRowKey, languageId);
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred during the user update.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method update.");
	}

	@Override
	public User findByEmail (User user) throws DataAccessException {
		logger.info ("Start executing the method findByEmail.");
		User foundUser = null;
		ArrayList<HashMap<String, String>> list = null;
		String[] columnFamilyArr = {
			UserColumnFamilies.ROLE_ROLE_ID.getColumnFamilyName (),
			UserColumnFamilies.INFO.getColumnFamilyName ()
		};
		String email = user.getEmail ();
		try {
			// getting all the records from the User table
			list = getTableByColumnFamilyName (Constants.USER_TABLENAME, columnFamilyArr);
			for (HashMap<String,String> map : list) {
				// checking if a map contains the user email
				if (map.containsValue (email)) {
					List<Role> roleList = new ArrayList<Role> ();
					// getting the user roles
					for (Map.Entry<String, String> keyValue : map.entrySet ()) {
						String key = keyValue.getKey ();
						if (key.startsWith (UserColumns.ROLE_ROLE_ID.getColumnName ())) {
							Integer roleId = new Integer (keyValue.getValue ());
							Role role = new Role ();
							role.setRoleId (roleId);
							roleList.add (role);
							logger.info ("roleId [" + roleId + "]");
						}
					}
					email = map.get (UserColumns.INFO_EMAIL.getColumnName ());
					String password = map.get (UserColumns.INFO_PASSWORD.getColumnName ());
					foundUser = new User ();
					foundUser.setEmail (email);
					foundUser.setPassword (password);
					foundUser.setRoleList (roleList);
					logger.info ("foundUser.getEmail() [" + foundUser.getEmail() + "]");
					break;
				}
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred when trying to find the user by email.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method findByEmail.");
		return foundUser;
	}		
	
	@Override
	public User findByEmailOrUsername (User user) throws DataAccessException {
		logger.info ("Start executing the method findByEmailOrUsername.");
		User foundUser = null;
		ArrayList<HashMap<String, String>> list = null;
		String[] columnFamilyArr = {
			UserColumnFamilies.INFO.getColumnFamilyName()
		};
		String[] columnNameArr = {
			UserColumns.INFO_USERNAME.getColumnName (),
			UserColumns.INFO_EMAIL.getColumnName (),
			UserColumns.INFO_PASSWORD.getColumnName ()
		};
		String[][] columnNamesBidimensionalArr = {
			columnNameArr
		};
		String email = user.getEmail ();
		String username = user.getUsername ();
		try {
			// getting all the records from the User table
			list = getTable (Constants.USER_TABLENAME, columnFamilyArr, columnNamesBidimensionalArr);
			for (HashMap<String,String> map : list) {
				// checking if a map contains the user email
				if (map.containsValue (email) || map.containsValue (username)) {
					email = map.get (UserColumns.INFO_EMAIL.getColumnName());
					username = map.get (UserColumns.INFO_USERNAME.getColumnName());
					foundUser = new User ();
					foundUser.setUsername (username);
					foundUser.setEmail (email);			
					logger.info ("foundUser.getUsername() [" + foundUser.getUsername() + "]");
					logger.info ("foundUser.getEmail() [" + foundUser.getEmail() + "]");
					break;
				}
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred when trying to find the user by email or username.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method findByEmailOrUsername.");
		return foundUser;
	}
	
	@Override
	public User findByUsername (String username) throws DataAccessException {
		logger.info ("Start executing the method findByUsername.");
		User foundUser = null;
		ArrayList<HashMap<String, String>> list = null;
		String[] columnFamilyArr = {
			UserColumnFamilies.INFO.getColumnFamilyName()
		};
		String[] columnNameArr = {
			UserColumns.INFO_USERNAME.getColumnName (),
			UserColumns.INFO_PASSWORD.getColumnName()
		};
		String[][] columnNamesBidimensionalArr = {
			columnNameArr
		};
		try {
			// getting all the records from the User table
			list = getTable (Constants.USER_TABLENAME, columnFamilyArr, columnNamesBidimensionalArr);
			for (HashMap<String,String> map : list) {
				// checking if a map contains the username
				if (map.containsValue (username)) {
					String usernameValue = map.get (UserColumns.INFO_USERNAME.getColumnName());
					String passwordValue = map.get (UserColumns.INFO_PASSWORD.getColumnName());
					foundUser = new User ();
					foundUser.setUsername (usernameValue);
					foundUser.setPassword (passwordValue);
					logger.info ("foundUser.getUsername() [" + foundUser.getUsername() + "]");
					break;
				}
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred when trying to find the user by username.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method findByUsername.");
		return foundUser;
	}
	
	@Override
	public boolean updatePassword (User user) throws DataAccessException {
		logger.info ("Start executing the method updatePassword.");
		boolean passwordUpdated = false;
		byte[] rowKey = null;
		Map<String,String> keyValueRowMap = null;
		String[] columnNameArr = {
			UserColumns.INFO_TOKEN_OPERATION.getColumnName()
		};
		try {
			// getting the row key according with the username
			rowKey = getRowKey (Constants.USER_TABLENAME, UserColumnFamilies.INFO.getColumnFamilyName(), UserColumns.INFO_USERNAME.getColumnName(), user.getUsername());
			if (rowKey != null && rowKey.length > 0) {
				// getting a map containing the key-value for the token operation
				// according with the row key associated to the user
				keyValueRowMap = getRow (Constants.USER_TABLENAME, rowKey, UserColumnFamilies.INFO.getColumnFamilyName(), columnNameArr);
				if (keyValueRowMap != null && keyValueRowMap.size() > 0) {
					String tokenOperation = keyValueRowMap.get (UserColumns.INFO_TOKEN_OPERATION.getColumnName());
					// checking if the user token operation is the same 
					// (from database) generated for the current operation
					// if so, the user password will be updated
					if (tokenOperation != null
							&& !"".equals (tokenOperation.trim())
							&& tokenOperation.equals (user.getTokenOperation())) {
						// encrypting the user password
						String digestedPassword = passwordEncoder.encodePassword (user.getPassword(), null);
						// updating the new user password
						addColumnEntry (Constants.USER_TABLENAME, UserColumnFamilies.INFO.getColumnFamilyName(), rowKey, UserColumns.INFO_PASSWORD.getColumnName(), digestedPassword);
						passwordUpdated = true;
					}
				}				
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred when trying to update the user password.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method updatePassword.");
		return passwordUpdated;
	}
	
	@Override
	public String generateTokenOperation (User user) throws DataAccessException {
		logger.info ("Start executing the method generateTokenOperation.");
		String token = null;
		byte[] rowKey = null;
		String username = user.getUsername ();
		token = "tk" + SecureRandomNumber.generateSecureRandomNumber ();
		try {
			// getting the row key according with the username
			rowKey = getRowKey (Constants.USER_TABLENAME, UserColumnFamilies.INFO.getColumnFamilyName(), UserColumns.INFO_USERNAME.getColumnName(), username);
			if (rowKey != null && rowKey.length > 0) {
				addColumnEntry (Constants.USER_TABLENAME, UserColumnFamilies.INFO.getColumnFamilyName(), rowKey, UserColumns.INFO_TOKEN_OPERATION.getColumnName(), token);
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred when trying to generate the token operation.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("token [" + token + "]");
		logger.info ("Finish executing the method generateTokenOperation.");
		return token;
	}		

}
