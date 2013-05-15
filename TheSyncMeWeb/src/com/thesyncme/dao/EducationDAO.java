package com.thesyncme.dao;

import java.util.List;

import com.thesyncme.business.entities.Education;
import com.thesyncme.exceptions.DataAccessException;

/**
 * Education DAO interface.
 * 
 * @author Josivan Ribeiro
 *
 */
public interface EducationDAO {

	/**
	 * Gets a list with all the education objects.
	 * 
	 * @return a list of education objects.
	 * @throws DataAccessException
	 */
	public List<Education> findAll () throws DataAccessException;
	
	/**
	 * Gets a list of education objects according with the institution name prefix.
	 * 
	 * @param prefix the institution name prefix.
	 * @return
	 * @throws DataAccessException
	 */
	public List<Education> findByInstitutionNamePrefix (String prefix) throws DataAccessException;
	
}
