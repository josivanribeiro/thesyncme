package com.thesyncme.dao;

import java.util.List;

import com.thesyncme.business.entities.Position;
import com.thesyncme.exceptions.DataAccessException;

/**
 * Position DAO interface.
 * 
 * @author Josivan Ribeiro
 *
 */
public interface PositionDAO {

	/**
	 * Gets a list of Position.
	 * 
	 * @return a list of Position.
	 * @throws DataAccessException
	 */
	public List<Position> findAll () throws DataAccessException;
	
	/**
	 * Finds a list of position according with the position name prefix.
	 * 
	 * @param positionNamePrefix the position name prefix.
	 * @return a list of position.
	 * @throws DataAccessException
	 */
	public List<Position> findByPositionNamePrefix (String positionNamePrefix) throws DataAccessException;
	
}
