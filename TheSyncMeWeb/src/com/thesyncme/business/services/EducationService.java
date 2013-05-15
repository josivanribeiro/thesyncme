package com.thesyncme.business.services;

import java.util.List;

import com.thesyncme.business.entities.Education;
import com.thesyncme.exceptions.BusinessException;
import com.thesyncme.exceptions.DataAccessException;

/**
 * Education business service interface.
 * 
 * @author Josivan Ribeiro
 *
 */
public interface EducationService {

	/**
	 * Gets a list of education.
	 * 
	 * @return a list of education.
	 * @throws BusinessException
	 */
	public List<Education> findAll () throws BusinessException;
	
	/**
	 * Finds a list of education according with the institution name prefix.
	 * 
	 * @param institutionNamePrefix the city name prefix.
	 * @return a list of education.
	 * @throws DataAccessException
	 */
	public List<Education> findByInstitutionNamePrefix (String institutionNamePrefix) throws BusinessException;
	
}
