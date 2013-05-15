package com.thesyncme.dao;

import com.thesyncme.business.entities.Place;
import com.thesyncme.exceptions.DataAccessException;

/**
 * Place DAO interface.
 * 
 * @author Josivan Ribeiro
 *
 */
public interface PlaceDAO {

	 /**
	* Creates a new place.
	* 
	* @param place the new place.
	* @throws DataAccessException 
	*/
	public void create (Place place) throws DataAccessException;
	
	/**
	 * Updates the place.
	 * 
	 * @param place the place.
	 * @throws DataAccessException
	 */
	public void update (Place place) throws DataAccessException;
	
	/**
	 * Finds a place given its email.
	 * 
	 * @param place the place.
	 * @return a Place instance.
	 * @throws DataAccessException
	 */
	public Place findByEmail (Place place) throws DataAccessException;
	
	/**
	 * Finds a place given its email or username.
	 * 
	 * @param place the place.
	 * @return a Place instance.
	 * @throws DataAccessException
	 */
	public Place findByEmailOrUsername (Place place) throws DataAccessException;
	
	/**
	 * Finds a place by its username.
	 * 
	 * @param username the username.
	 * @throws DataAccessException
	 */
	public Place findByUsername (String username) throws DataAccessException;
	
	/**
	 * Updates the place password.
	 * 
	 * @param place the place.
	 * @return a boolean indicating whether the user password has been successfully updated.
	 * @throws DataAccessException
	 */
	public boolean updatePassword (Place place) throws DataAccessException;
	
	/**
	 * Generates a new token operation.
	 *
	 * @param place the place.
	 * @return a string representing the new token operation.
	 * @throws DataAccessException
	 */
	public String generateTokenOperation (Place place) throws DataAccessException;	
	
}
