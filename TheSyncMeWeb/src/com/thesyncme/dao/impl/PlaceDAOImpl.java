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

import com.thesyncme.business.entities.Place;
import com.thesyncme.business.entities.Role;
import com.thesyncme.constants.Constants;
import com.thesyncme.dao.AbstractDAO;
import com.thesyncme.dao.PlaceDAO;
import com.thesyncme.enums.PlaceColumnFamilies;
import com.thesyncme.enums.PlaceColumns;
import com.thesyncme.enums.UserColumnFamilies;
import com.thesyncme.exceptions.DataAccessException;
import com.thesyncme.helper.Helper;
import com.thesyncme.security.SecureRandomNumber;

@Repository
public class PlaceDAOImpl extends AbstractDAO implements PlaceDAO {
	/**
	 * Default logger.
	 */
	private static Logger logger = Logger.getLogger (PlaceDAOImpl.class);
	/**
	 * Attribute that defines the default password encoder.
	 */
	private PasswordEncoder passwordEncoder;
		
	public void setPasswordEncoder (PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void create (Place place) throws DataAccessException {
		logger.info ("Start executing the method create.");
		try {
			if (passwordEncoder == null) {
				StandardStringDigester standardStringDigester = new StandardStringDigester ();
				standardStringDigester.setAlgorithm ("SHA-1");
				standardStringDigester.setIterations (50000);
				passwordEncoder = new PasswordEncoder ();
				passwordEncoder.setStringDigester (standardStringDigester);
			}
			// encrypting the place password
			String digestedPassword = passwordEncoder.encodePassword (place.getPassword(), null);
			String[] columnNameArr = {
				PlaceColumns.INFO_USERNAME.getColumnName (),
				PlaceColumns.INFO_EMAIL.getColumnName (),
				PlaceColumns.INFO_PASSWORD.getColumnName ()
			};
			String[] dataArr = {
				place.getUsername (), place.getEmail (), digestedPassword
			};
			String rowKey = place.getUsername ();
			addRecordWithSingleColumnFamily (Constants.PLACE_TABLENAME, rowKey, PlaceColumnFamilies.INFO.getColumnFamilyName (), columnNameArr, dataArr);
			// updating the place roles
			for (Role role : place.getRoleList()) {
				String roleId = role.getRoleId ().toString ();
				String roleIdRowKey = PlaceColumns.ROLE_ROLE_ID.getColumnName() + "_" + Helper.getCurrentTimestamp ();
				addColumnEntry (Constants.PLACE_TABLENAME, PlaceColumnFamilies.ROLE_ROLE_ID.getColumnFamilyName (), rowKey, roleIdRowKey, roleId);
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred during the place creation.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method create.");		
	}

	@Override
	public void update (Place place) throws DataAccessException {
		logger.info ("Start executing the method update.");
		String rowKey = place.getUsername ();
		try {
			// updating the place about info
			addColumnEntry (Constants.PLACE_TABLENAME, PlaceColumnFamilies.INFO.getColumnFamilyName(), rowKey, PlaceColumns.INFO_ABOUT.getColumnName(), place.getAbout());
			// updating the place website info
			addColumnEntry (Constants.PLACE_TABLENAME, PlaceColumnFamilies.INFO.getColumnFamilyName(), rowKey, PlaceColumns.INFO_WEBSITE.getColumnName(), place.getWebsite());
			// updating the place location
			String locationId = place.getLocation().getLocationId().toString();
			addColumnEntry (Constants.PLACE_TABLENAME, PlaceColumnFamilies.LOCATION_LOCATION_ID.getColumnFamilyName(), rowKey, PlaceColumns.LOCATION_LOCATION_ID.getColumnName(), locationId);
			// updating the place address
			String addressId = place.getAddress().getAddressId().toString();
			addColumnEntry (Constants.PLACE_TABLENAME, PlaceColumnFamilies.ADDRESS_ADDRESS_ID.getColumnFamilyName(), rowKey, PlaceColumns.ADDRESS_ADDRESS_ID.getColumnName(), addressId);
			// updating the place phones
				// removing the old entries for the place phones before updating them
			removeColumnEntries (Constants.PLACE_TABLENAME, rowKey, PlaceColumnFamilies.INFO.getColumnFamilyName(), PlaceColumns.INFO_PHONE.getColumnName());
			for (String phone : place.getPhoneList()) {
				String phoneRowKey = PlaceColumns.INFO_PHONE.getColumnName() + "_" + Helper.getCurrentTimestamp();
				addColumnEntry (Constants.PLACE_TABLENAME, PlaceColumnFamilies.INFO.getColumnFamilyName(), rowKey, phoneRowKey, phone);
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred during the place update.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method update.");
		
	}
	
	@Override
	public Place findByEmail (Place place) throws DataAccessException {
		logger.info ("Start executing the method findByEmail.");
		Place foundPlace = null;
		ArrayList<HashMap<String, String>> list = null;
		String[] columnFamilyArr = {
			UserColumnFamilies.ROLE_ROLE_ID.getColumnFamilyName (),
			PlaceColumnFamilies.INFO.getColumnFamilyName()
		};
		String email = place.getEmail ();		
		try {
			// getting all the records from the Place table
			list = getTableByColumnFamilyName(Constants.PLACE_TABLENAME, columnFamilyArr);
			for (HashMap<String,String> map : list) {
				// checking if a map contains the place email
				if (map.containsValue (email)) {
					List<Role> roleList = new ArrayList<Role> ();
					// getting the user roles
					for (Map.Entry<String, String> keyValue : map.entrySet ()) {
						String key = keyValue.getKey ();
						if (key.startsWith (PlaceColumns.ROLE_ROLE_ID.getColumnName ())) {
							Integer roleId = new Integer (keyValue.getValue ());
							Role role = new Role ();
							role.setRoleId (roleId);
							roleList.add (role);
							logger.info ("roleId [" + roleId + "]");
						}
					}
					email = map.get (PlaceColumns.INFO_EMAIL.getColumnName());
					String password = map.get (PlaceColumns.INFO_PASSWORD.getColumnName());
					foundPlace = new Place ();
					foundPlace.setEmail (email);
					foundPlace.setPassword (password);
					foundPlace.setRoleList (roleList);
					logger.info ("foundPlace.getEmail() [" + foundPlace.getEmail() + "]");
					break;
				}
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred when trying to find the place by email.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method findByEmail.");
		return foundPlace;
	}
	
	@Override
	public Place findByEmailOrUsername (Place place) throws DataAccessException {
		logger.info ("Start executing the method findByEmailOrUsername.");
		Place foundPlace = null;
		ArrayList<HashMap<String, String>> list = null;
		String[] columnFamilyArr = {
			PlaceColumnFamilies.INFO.getColumnFamilyName()
		};
		String[] columnNameArr = {
			PlaceColumns.INFO_USERNAME.getColumnName (),
			PlaceColumns.INFO_EMAIL.getColumnName (),
			PlaceColumns.INFO_PASSWORD.getColumnName ()
		};
		String[][] columnNamesBidimensionalArr = {
			columnNameArr
		};
		String email = place.getEmail ();
		String username = place.getUsername ();
		try {
			// getting all the records from the Place table
			list = getTable (Constants.PLACE_TABLENAME, columnFamilyArr, columnNamesBidimensionalArr);
			for (HashMap<String,String> map : list) {
				// checking if a map contains the place email
				if (map.containsValue (email) || map.containsValue (username)) {
					email = map.get (PlaceColumns.INFO_EMAIL.getColumnName());
					username = map.get (PlaceColumns.INFO_USERNAME.getColumnName());
					foundPlace = new Place ();
					foundPlace.setUsername (username);
					foundPlace.setEmail (email);			
					logger.info ("foundPlace.getUsername() [" + foundPlace.getUsername() + "]");
					logger.info ("foundPlace.getEmail() [" + foundPlace.getEmail() + "]");
					break;
				}
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred when trying to find the place by email or username.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method findByEmailOrUsername.");
		return foundPlace;
	}

	@Override
	public Place findByUsername (String username) throws DataAccessException {
		logger.info ("Start executing the method findByUsername.");
		Place foundPlace = null;
		ArrayList<HashMap<String, String>> list = null;
		String[] columnFamilyArr = {
			PlaceColumnFamilies.INFO.getColumnFamilyName()
		};
		String[] columnNameArr = {
			PlaceColumns.INFO_USERNAME.getColumnName ()
		};
		String[][] columnNamesBidimensionalArr = {
			columnNameArr
		};
		try {
			// getting all the records from the Place table
			list = getTable (Constants.PLACE_TABLENAME, columnFamilyArr, columnNamesBidimensionalArr);
			for (HashMap<String,String> map : list) {
				// checking if the map contains the username
				if (map.containsValue (username)) {
					String usernameValue = map.get (PlaceColumns.INFO_USERNAME.getColumnName());
					foundPlace = new Place ();
					foundPlace.setUsername (usernameValue);
					logger.info ("foundPlace.getUsername() [" + foundPlace.getUsername() + "]");
					break;
				}
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred when trying to find the place by username.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method findByUsername.");
		return foundPlace;
	}

	@Override
	public boolean updatePassword (Place place) throws DataAccessException {
		logger.info ("Start executing the method updatePassword.");
		boolean passwordUpdated = false;
		byte[] rowKey = null;
		Map<String,String> keyValueRowMap = null;
		String[] columnNameArr = {
			PlaceColumns.INFO_TOKEN_OPERATION.getColumnName()
		};
		try {
			// getting the row key according with the username
			rowKey = getRowKey (Constants.PLACE_TABLENAME, PlaceColumnFamilies.INFO.getColumnFamilyName(), PlaceColumns.INFO_USERNAME.getColumnName(), place.getUsername());
			if (rowKey != null && rowKey.length > 0) {
				// getting a map containing the key-value for the token operation
				// according with the row key associated to the user
				keyValueRowMap = getRow (Constants.PLACE_TABLENAME, rowKey, PlaceColumnFamilies.INFO.getColumnFamilyName(), columnNameArr);
				if (keyValueRowMap != null && keyValueRowMap.size() > 0) {
					String tokenOperation = keyValueRowMap.get (PlaceColumns.INFO_TOKEN_OPERATION.getColumnName());
					// checking if the place token operation is the same 
					// (from database) generated for the current operation
					// if so, the user password will be updated
					if (tokenOperation != null
							&& !"".equals (tokenOperation.trim())
							&& tokenOperation.equals (place.getTokenOperation())) {
						// encrypting the place password
						String digestedPassword = passwordEncoder.encodePassword (place.getPassword(), null);
						// updating the new place password
						addColumnEntry (Constants.PLACE_TABLENAME, PlaceColumnFamilies.INFO.getColumnFamilyName(), rowKey, PlaceColumns.INFO_PASSWORD.getColumnName(), digestedPassword);
						passwordUpdated = true;
					}
				}			
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred when trying to update the place password.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method updatePassword.");
		return passwordUpdated;
	}

	@Override
	public String generateTokenOperation (Place place) throws DataAccessException {
		logger.info ("Start executing the method generateTokenOperation.");
		String token = null;
		byte[] rowKey = null;
		String username = place.getUsername ();
		token = "tk" + SecureRandomNumber.generateSecureRandomNumber ();
		try {
			// getting the row key according with the username
			rowKey = getRowKey (Constants.PLACE_TABLENAME, PlaceColumnFamilies.INFO.getColumnFamilyName(), PlaceColumns.INFO_USERNAME.getColumnName(), username);
			if (rowKey != null && rowKey.length > 0) {
				addColumnEntry (Constants.PLACE_TABLENAME, PlaceColumnFamilies.INFO.getColumnFamilyName(), rowKey, PlaceColumns.INFO_TOKEN_OPERATION.getColumnName(), token);
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
