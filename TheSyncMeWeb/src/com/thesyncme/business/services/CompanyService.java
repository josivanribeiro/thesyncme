package com.thesyncme.business.services;

import java.util.List;

import com.thesyncme.business.entities.Company;
import com.thesyncme.exceptions.BusinessException;
import com.thesyncme.exceptions.DataAccessException;

/**
 * Company business service interface.
 * 
 * @author Josivan Ribeiro
 *
 */
public interface CompanyService {

	/**
	 * Gets a list of company.
	 * 
	 * @return a list of company.
	 * @throws BusinessException
	 */
	public List<Company> findAll () throws BusinessException;
	
	/**
	 * Finds a list of company according with the company name prefix.
	 * 
	 * @param companyNamePrefix the company name prefix.
	 * @return a list of company.
	 * @throws DataAccessException
	 */
	public List<Company> findByCompanyNamePrefix (String companyNamePrefix) throws BusinessException;
	
}
