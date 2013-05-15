package com.thesyncme.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.thesyncme.business.entities.Company;
import com.thesyncme.business.services.CompanyService;
import com.thesyncme.dao.impl.CompanyDAOImpl;
import com.thesyncme.exceptions.BusinessException;
import com.thesyncme.exceptions.DataAccessException;

@Service ("companyService")
public class CompanyServiceImpl implements CompanyService {
	/**
	 * Default logger.
	 */
	private static Logger logger = Logger.getLogger (CompanyServiceImpl.class);
	/**
	 * Attribute that defines the Company DAO instance.
	 */
	private CompanyDAOImpl companyDAO;
	
	public void setCompanyDAO (CompanyDAOImpl companyDAO) {
		this.companyDAO = companyDAO;
	}

	@Override
	public List<Company> findAll() throws BusinessException {
		List<Company> companyList = new ArrayList<Company>();
		logger.info ("Start executing the method findAll().");
		try {
			companyList = companyDAO.findAll();			
		}
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding a company list.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findAll().");
		return companyList;
	}

	@Override
	public List<Company> findByCompanyNamePrefix (String companyNamePrefix) throws BusinessException {
		List<Company> companyList = new ArrayList<Company>();
		logger.info ("Start executing the method findByCompanyNamePrefix().");
		logger.info ("institutionNamePrefix [" + companyNamePrefix + "]");
		try {
			companyList = companyDAO.findByCompanyNamePrefix (companyNamePrefix);
		}
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding a company by its name prefix.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findByCompanyNamePrefix().");
		return companyList;
	}	
	
}
