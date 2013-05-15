package com.thesyncme.dao;

import java.util.List;

import com.thesyncme.business.entities.Role;
import com.thesyncme.exceptions.DataAccessException;

/**
 * Role DAO interface.
 * 
 * @author Josivan Ribeiro
 *
 */
public interface RoleDAO {

	/**
	 * Gets a list of Role.
	 * 
	 * @return a list of Role.
	 * @throws DataAccessException
	 */
	public List<Role> findAll () throws DataAccessException;
	
	/**
	 * Finds a role given its id.
	 * 
	 * @param roleId the role id.
	 * @return a Role object.
	 * @throws DataAccessException
	 */
	public Role findById (String roleId) throws DataAccessException;
	
}
