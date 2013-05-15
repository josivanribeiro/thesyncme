package com.thesyncme.dao;

import java.util.List;

import com.thesyncme.business.entities.Company;
import com.thesyncme.exceptions.DataAccessException;

/**
 * Company DAO interface.
 * 
 * @author Josivan Ribeiro
 *
 */
public interface CompanyDAO {

	/**
	 * Gets a list of Company.
	 * 
	 * @return a list of Company.
	 * @throws DataAccessException
	 */
	public List<Company> findAll () throws DataAccessException;
	
	/**
	 * Finds a list of company according with the company name prefix.
	 * 
	 * @param companyNamePrefix the company name prefix.
	 * @return a list of company.
	 * @throws DataAccessException
	 */
	public List<Company> findByCompanyNamePrefix (String companyNamePrefix) throws DataAccessException;
	
}
