package com.thesyncme.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.thesyncme.business.entities.Education;
import com.thesyncme.business.services.EducationService;
import com.thesyncme.dao.impl.EducationDAOImpl;
import com.thesyncme.exceptions.BusinessException;
import com.thesyncme.exceptions.DataAccessException;

@Service ("educationService")
public class EducationServiceImpl implements EducationService {
	/**
	 * Default logger.
	 */
	private static Logger logger = Logger.getLogger (EducationServiceImpl.class);
	/**
	 * Attribute that defines the Education DAO instance.
	 */
	private EducationDAOImpl educationDAO;
	
	public void setEducationDAO (EducationDAOImpl educationDAO) {
		this.educationDAO = educationDAO;
	}

	@Override
	public List<Education> findAll () throws BusinessException {
		List<Education> educationList = new ArrayList<Education>();
		logger.info ("Start executing the method findAll().");
		try {
			educationList = educationDAO.findAll();			
		}
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding an education list.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findAll().");
		return educationList;
	}

	@Override
	public List<Education> findByInstitutionNamePrefix (String institutionNamePrefix) throws BusinessException {
		List<Education> educationList = new ArrayList<Education>();
		logger.info ("Start executing the method findByInstitutionNamePrefix().");
		logger.info ("institutionNamePrefix [" + institutionNamePrefix + "]");
		try {
			educationList = educationDAO.findByInstitutionNamePrefix (institutionNamePrefix);
		}
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding an institution by the name prefix.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findByInstitutionNamePrefix().");
		return educationList;
	}

}
