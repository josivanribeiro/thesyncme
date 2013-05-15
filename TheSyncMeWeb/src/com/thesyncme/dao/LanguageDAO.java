package com.thesyncme.dao;

import java.util.List;

import com.thesyncme.business.entities.Language;
import com.thesyncme.exceptions.DataAccessException;

/**
 * Language DAO interface.
 * 
 * @author Josivan Ribeiro
 *
 */
public interface LanguageDAO {

	/**
	 * Gets a list of Language.
	 * 
	 * @return a list of Language.
	 * @throws DataAccessException
	 */
	public List<Language> findAll () throws DataAccessException;
	
	/**
	 * Finds a list of language according with the language name prefix.
	 * 
	 * @param languageNamePrefix the position name prefix.
	 * @return a list of language.
	 * @throws DataAccessException
	 */
	public List<Language> findByLanguageNamePrefix (String languageNamePrefix) throws DataAccessException;
	
}
