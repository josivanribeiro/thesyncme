package com.thesyncme.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.thesyncme.business.entities.Role;
import com.thesyncme.constants.Constants;
import com.thesyncme.dao.AbstractDAO;
import com.thesyncme.dao.RoleDAO;
import com.thesyncme.enums.RoleColumns;
import com.thesyncme.exceptions.DataAccessException;

@Repository
public class RoleDAOImpl extends AbstractDAO implements RoleDAO {
	/**
	 * Default logger.
	 */
	private static Logger logger = Logger.getLogger (RoleDAOImpl.class);
	/**
	 * Attribute that defines the column family name array.
	 */
	private String[] columnFamilyNameArr = {
		"Role"
	};
	/**
	 * Attribute that defines the column name array.
	 */
	private String[] columnNameArr = {
		RoleColumns.ROLE_ID.getColumnName (),
		RoleColumns.NAME.getColumnName ()
	};
	
	@Override
	public List<Role> findAll() throws DataAccessException {
		logger.info ("Start executing the method findAll.");
		List<Role> roleList = new ArrayList<Role> ();
		ArrayList<HashMap<String, String>> list = null;
		String[][] columnNamesBidimensionalArr = {
			columnNameArr
		};
		try {
			// getting all the records from the Role table
			list = getTable (Constants.ROLE_TABLENAME, columnFamilyNameArr, columnNamesBidimensionalArr);
			for (HashMap<String,String> map : list) {
				String roleId = map.get (RoleColumns.ROLE_ID.getColumnName());
				String name = map.get (RoleColumns.NAME.getColumnName());
				Role role = new Role ();
				role.setRoleId (new Integer (roleId));
				role.setName (name);
				roleList.add (role);
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred during the findAll operation.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method findAll.");
		return roleList;
	}

	@Override
	public Role findById (String roleId) throws DataAccessException {
		logger.info ("Start executing the method findById.");
		Role foundRole = null;
		ArrayList<HashMap<String, String>> list = null;
		String[] columnNameArr = {
			RoleColumns.ROLE_ID.getColumnName(),
			RoleColumns.NAME.getColumnName()
		};
		String[][] columnNamesBidimensionalArr = {
			columnNameArr
		};
		try {
			// getting all the records from the Role table
			list = getTable (Constants.ROLE_TABLENAME, columnFamilyNameArr, columnNamesBidimensionalArr);
			for (HashMap<String,String> map : list) {
				// checking if a map contains the roleId
				if (map.containsValue (roleId)) {
					String roleName = map.get (RoleColumns.NAME.getColumnName());
					foundRole = new Role ();
					foundRole.setRoleId (new Integer (roleId));
					foundRole.setName (roleName);
					logger.info ("foundRole.getRoleId().toString() [" + foundRole.getRoleId().toString() + "]");
					logger.info ("foundRole.getName() [" + foundRole.getName() + "]");
					break;
				}
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred when trying to find the role by id.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method findById.");
		return foundRole;
	}

}
