package com.thesyncme.business.services;

import java.util.List;

import com.thesyncme.business.entities.Location;
import com.thesyncme.exceptions.BusinessException;
import com.thesyncme.exceptions.DataAccessException;

/**
 * Location business service interface.
 * 
 * @author Josivan Ribeiro
 *
 */
public interface LocationService {

	/**
	 * Gets a list of location.
	 * 
	 * @return a list of location.
	 * @throws BusinessException
	 */
	public List<Location> findAll () throws BusinessException;
	
	/**
	 * Finds a list of location according with the city name prefix.
	 * 
	 * @param cityNamePrefix the city name prefix.
	 * @return a list of location.
	 * @throws DataAccessException
	 */
	public List<Location> findByCityNamePrefix (String cityNamePrefix) throws BusinessException;
	
}
