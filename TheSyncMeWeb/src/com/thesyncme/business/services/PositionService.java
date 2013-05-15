package com.thesyncme.business.services;

import java.util.List;

import com.thesyncme.business.entities.Position;
import com.thesyncme.exceptions.BusinessException;
import com.thesyncme.exceptions.DataAccessException;

/**
 * Position business service interface.
 * 
 * @author Josivan Ribeiro
 *
 */
public interface PositionService {

	/**
	 * Gets a list of Position.
	 * 
	 * @return a list of position.
	 * @throws BusinessException
	 */
	public List<Position> findAll () throws BusinessException;
	
	/**
	 * Finds a list of Position according with the position name prefix.
	 * 
	 * @param positionNamePrefix the position name prefix.
	 * @return a list of position.
	 * @throws DataAccessException
	 */
	public List<Position> findByPositionNamePrefix (String positionNamePrefix) throws BusinessException;
	
}
