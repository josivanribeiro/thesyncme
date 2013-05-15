package com.thesyncme.business.services;

import java.util.List;

import com.thesyncme.business.entities.Language;
import com.thesyncme.exceptions.BusinessException;
import com.thesyncme.exceptions.DataAccessException;

/**
 * Language business service interface.
 * 
 * @author Josivan Ribeiro
 *
 */
public interface LanguageService {

	/**
	 * Gets a list of Language.
	 * 
	 * @return a list of language.
	 * @throws BusinessException
	 */
	public List<Language> findAll () throws BusinessException;
	
	/**
	 * Finds a list of Language according with the language name prefix.
	 * 
	 * @param languageNamePrefix the position name prefix.
	 * @return a list of language.
	 * @throws DataAccessException
	 */
	public List<Language> findByLanguageNamePrefix (String languageNamePrefix) throws BusinessException;
	
}
