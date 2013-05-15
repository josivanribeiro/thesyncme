package com.thesyncme.dao;

import java.util.List;

import com.thesyncme.business.entities.Location;
import com.thesyncme.exceptions.DataAccessException;

/**
 * Location DAO interface.
 * 
 * @author Josivan Ribeiro
 *
 */
public interface LocationDAO {

	/**
	 * Gets a list of Location.
	 * 
	 * @return a list of Location.
	 * @throws DataAccessException
	 */
	public List<Location> findAll () throws DataAccessException;
	
	/**
	 * Finds a list of location according with the city name prefix.
	 * 
	 * @param cityNamePrefix the city name prefix.
	 * @return a list of location.
	 * @throws DataAccessException
	 */
	public List<Location> findByCityNamePrefix (String cityNamePrefix) throws DataAccessException;
	
}
