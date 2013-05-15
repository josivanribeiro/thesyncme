package com.thesyncme.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.thesyncme.business.entities.Language;
import com.thesyncme.constants.Constants;
import com.thesyncme.dao.AbstractDAO;
import com.thesyncme.dao.LanguageDAO;
import com.thesyncme.exceptions.DataAccessException;

@Repository
public class LanguageDAOImpl extends AbstractDAO implements LanguageDAO {
	/**
	 * Default logger.
	 */
	private static Logger logger = Logger.getLogger (LanguageDAOImpl.class);
	/**
	 * Attribute that defines the column family name array.
	 */
	private String[] columnFamilyNameArr = {
		"Language"
	};
	/**
	 * Attribute that defines the column name array.
	 */
	private String[] columnNameArr = {
		LanguageColumns.LANGUAGE_ID.getColumnName (),
		LanguageColumns.NAME.getColumnName ()
	};
	/**
	 * Position enum columns.
	 *
	 */
	private enum LanguageColumns {
		LANGUAGE_ID ("languageId"), 
		NAME ("name");
						
		private String columnName;
				
		private LanguageColumns (String columnName) {
			this.columnName = columnName;
		}
		
		public String getColumnName () {
			return columnName;
		}		
	}	
	
	@Override
	public List<Language> findAll() throws DataAccessException {
		logger.info ("Start executing the method findAll.");
		List<Language> languageList = new ArrayList<Language> ();
		ArrayList<HashMap<String, String>> list = null;
		String[][] columnNamesBidimensionalArr = {
			columnNameArr
		};
		try {
			// getting all the records from the Position table
			list = getTable (Constants.LANGUAGE_TABLENAME, columnFamilyNameArr, columnNamesBidimensionalArr);
			for (HashMap<String,String> map : list) {
				String languageId = map.get (LanguageColumns.LANGUAGE_ID.getColumnName());
				String name = map.get (LanguageColumns.NAME.getColumnName());		
				Language language = new Language ();
				language.setLanguageId (new Integer (languageId));
				language.setName (name);
				languageList.add (language);
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred during the findAll operation.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method findAll.");
		return languageList;
	}

	@Override
	public List<Language> findByLanguageNamePrefix (String languageNamePrefix) throws DataAccessException {
		logger.info ("Start executing the method findByLanguageNamePrefix.");
		List<Language> languageList = new ArrayList<Language> ();
		ArrayList<HashMap<String, String>> list = null;
		String[][] columnNamesBidimensionalArr = {
			columnNameArr
		};
		try {
			// getting all the records from the Company table
			list = getTable (Constants.LANGUAGE_TABLENAME, columnFamilyNameArr, columnNamesBidimensionalArr);
			for (HashMap<String,String> map : list) {
				String languageName = map.get (LanguageColumns.NAME.getColumnName());
				if (languageName != null && !"".equals (languageName.trim())) {
					if (languageName.startsWith (languageNamePrefix)) {
						String languageId = map.get (LanguageColumns.LANGUAGE_ID.getColumnName());						
						Language language = new Language ();
						language.setLanguageId (new Integer (languageId));
						language.setName (languageName);						
						languageList.add (language);
					}
				}
			}			
		} catch (IOException e) {
			String errorMessage = "A data access error occurred during the search by language name prefix operation.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method findByLanguageNamePrefix.");
		return languageList;
	}

}
