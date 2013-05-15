package com.thesyncme.business.services;

import com.thesyncme.business.entities.Place;
import com.thesyncme.exceptions.BusinessException;
import com.thesyncme.exceptions.DataAccessException;

/**
 * Place business service interface.
 * 
 * @author Josivan Ribeiro
 *
 */
public interface PlaceService {

	/**
	 * Creates a new place.
	 * 
	 * @param user the new user.
	 * @throws BusinessException
	 */
	public void createPlace (Place place) throws BusinessException;
	
	/**
	 * Updates the place.
	 * 
	 * @param place the place.
	 * @throws BusinessException
	 */
	public void updatePlace (Place place) throws BusinessException;
	
	/**
	 * Finds a place given its email or username.
	 * 
	 * @param place the place.
	 * @return a Place instance.
	 * @throws BusinessException
	 */
	public Place findByEmailOrUsername (Place place) throws BusinessException;
	
	/**
	 * Finds a place by its username.
	 * 
	 * @param username the username.
	 * @throws DataAccessException
	 */
	public Place findByUsername (String username) throws BusinessException;
	
	/**
	 * Updates the place password given its username.
	 * 
	 * @param place the place.
	 * @return a boolean indicating if the password has been successfully updated.
	 * @throws BusinessException
	 */
	public boolean updatePassword (Place place) throws BusinessException;
	
	/**
	 * Generates a token operation.
	 * 
	 * @param place the user.
	 * @return a string representing the token operation.
	 * @throws BusinessException
	 */
	public String generateTokenOperation (Place place) throws BusinessException;
	
}
