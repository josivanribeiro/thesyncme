package com.thesyncme.business.services;

import com.thesyncme.business.entities.User;
import com.thesyncme.exceptions.BusinessException;
import com.thesyncme.exceptions.DataAccessException;

/**
 * User business service interface.
 * 
 * @author Josivan Ribeiro
 *
 */
public interface UserService {

	/**
	 * Creates a new user.
	 * 
	 * @param user the new user.
	 * @throws BusinessException
	 */
	public void createUser (User user) throws BusinessException;
	
	/**
	 * Updates the user.
	 * 
	 * @param user the user.
	 * @throws DataAccessException
	 */
	public void updateUser (User user) throws BusinessException;
	
	/**
	 * Finds a user given its email or username.
	 * 
	 * @param user the user.
	 * @return a User instance.
	 * @throws BusinessException
	 */
	public User findByEmailOrUsername (User user) throws BusinessException;
	
	/**
	 * Finds a user by its username.
	 * 
	 * @param username the username.
	 * @throws DataAccessException
	 */
	public User findByUsername (String username) throws BusinessException;
	
	/**
	 * Updates the user password given its id.
	 * 
	 * @param user the user id.
	 * @return a boolean indicating if the password has been successfully updated.
	 * @throws BusinessException
	 */
	public boolean updatePassword (User user) throws BusinessException;
	
	/**
	 * Generates a token operation.
	 * 
	 * @param user the user.
	 * @return a string representing the token operation.
	 * @throws BusinessException
	 */
	public String generateTokenOperation (User user) throws BusinessException;
	
}
