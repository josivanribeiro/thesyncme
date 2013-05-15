package com.thesyncme.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.thesyncme.business.entities.Language;
import com.thesyncme.business.services.LanguageService;
import com.thesyncme.dao.impl.LanguageDAOImpl;
import com.thesyncme.exceptions.BusinessException;
import com.thesyncme.exceptions.DataAccessException;

@Service ("languageService")
public class LanguageServiceImpl implements LanguageService {
	/**
	 * Default logger.
	 */
	private static Logger logger = Logger.getLogger (LanguageServiceImpl.class);
	/**
	 * Attribute that defines the Language DAO instance.
	 */
	private LanguageDAOImpl languageDAO;
	
	public void setLanguageDAO (LanguageDAOImpl languageDAO) {
		this.languageDAO = languageDAO;
	}

	@Override
	public List<Language> findAll() throws BusinessException {
		List<Language> languageList = new ArrayList<Language>();
		logger.info ("Start executing the method findAll().");
		try {
			languageList = languageDAO.findAll();			
		}
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding a language list.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findAll().");
		return languageList;
	}

	@Override
	public List<Language> findByLanguageNamePrefix (String languageNamePrefix) throws BusinessException {
		List<Language> languageList = new ArrayList<Language>();
		logger.info ("Start executing the method findByLanguageNamePrefix().");
		logger.info ("languageNamePrefix [" + languageNamePrefix + "]");
		try {
			languageList = languageDAO.findByLanguageNamePrefix (languageNamePrefix);
		}
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding a language by its name prefix.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findByLanguageNamePrefix().");
		return languageList;
	}

}
