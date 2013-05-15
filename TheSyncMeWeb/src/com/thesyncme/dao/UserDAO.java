package com.thesyncme.dao;

import com.thesyncme.business.entities.User;
import com.thesyncme.exceptions.DataAccessException;

/**
 * User DAO interface.
 * 
 * @author Josivan Ribeiro
 *
 */
public interface UserDAO {

    /**
	* Creates a new user.
	* 
	* @param user the new user.
	* @throws DataAccessException 
	*/
	public void create (User user) throws DataAccessException;
	
	/**
	 * Updates the user.
	 * 
	 * @param user the user.
	 * @throws DataAccessException
	 */
	public void update (User user) throws DataAccessException;
	
	/**
	 * Finds a user given its email.
	 * 
	 * @param user the user.
	 * @return a User instance.
	 * @throws DataAccessException
	 */
	public User findByEmail (User user) throws DataAccessException;
	
	/**
	 * Finds a user given its email or username.
	 * 
	 * @param user the user.
	 * @return a User instance.
	 * @throws DataAccessException
	 */
	public User findByEmailOrUsername (User user) throws DataAccessException;
	
	/**
	 * Updates the user password.
	 * 
	 * @param user the user.
	 * @return a boolean indicating whether the user password has been successfully updated.
	 * @throws DataAccessException
	 */
	public boolean updatePassword (User user) throws DataAccessException;
	
	/**
	 * Generates a new token operation.
	 *
	 * @param user the user.
	 * @return a string representing the new token operation.
	 * @throws DataAccessException
	 */
	public String generateTokenOperation (User user) throws DataAccessException;
	
	/**
	 * Finds a user by its username.
	 * 
	 * @param username the username.
	 * @throws DataAccessException
	 */
	public User findByUsername (String username) throws DataAccessException;
	
}
