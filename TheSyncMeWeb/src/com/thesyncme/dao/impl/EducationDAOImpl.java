package com.thesyncme.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.thesyncme.business.entities.Education;
import com.thesyncme.business.entities.EducationType;
import com.thesyncme.constants.Constants;
import com.thesyncme.dao.AbstractDAO;
import com.thesyncme.dao.EducationDAO;
import com.thesyncme.exceptions.DataAccessException;

@Repository
public class EducationDAOImpl extends AbstractDAO implements EducationDAO {
	/**
	 * Default logger.
	 */
	private static Logger logger = Logger.getLogger (EducationDAOImpl.class);
	/**
	 * Attribute that defines the column family name array.
	 */
	private String[] columnFamilyNameArr = {
		EducationType.SCHOOL.getName(), EducationType.COLLEGE.getName()
	};
	/**
	 * Attribute that defines the column name array.
	 */
	private String[] columnNameArr = {
		EducationColumns.EDUCATION_ID.getColumnName (),
		EducationColumns.INSTITUTION_NAME.getColumnName ()
	};
	/**
	 * Education enum columns.
	 *
	 */
	private enum EducationColumns {
		EDUCATION_ID ("educationId"), 
		INSTITUTION_NAME ("institutionName");
						
		private String columnName;
				
		private EducationColumns (String columnName) {
			this.columnName = columnName;
		}
		
		public String getColumnName () {
			return columnName;
		}		
	}	
	
	@Override
	public List<Education> findAll() throws DataAccessException {
		logger.info ("Start executing the method findAll.");
		List<Education> educationList = new ArrayList<Education> ();
		ArrayList<HashMap<String, String>> list = null;
		String[][] columnNamesBidimensionalArr = {
			columnNameArr, columnNameArr
		};
		try {
			// getting all the records from the Education table
			list = getTable (Constants.EDUCATION_TABLENAME, columnFamilyNameArr, columnNamesBidimensionalArr);
			for (HashMap<String,String> map : list) {
				String educationId = map.get (EducationColumns.EDUCATION_ID.getColumnName());
				String institutionName = map.get (EducationColumns.INSTITUTION_NAME.getColumnName());		
				Education education = new Education ();
				education.setEducationId (new Long(educationId));
				education.setInstitutionName (institutionName);
				educationList.add (education);
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred during the findAll operation.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method findAll.");
		return educationList;
	}


	@Override
	public List<Education> findByInstitutionNamePrefix (String prefix) throws DataAccessException {
		logger.info ("Start executing the method findByInstitutionNamePrefix.");
		List<Education> educationList = new ArrayList<Education> ();
		ArrayList<HashMap<String, String>> list = null;
		String[][] columnNamesBidimensionalArr = {
			columnNameArr, columnNameArr
		};
		try {
			// getting all the records from the Education table
			list = getTable (Constants.EDUCATION_TABLENAME, columnFamilyNameArr, columnNamesBidimensionalArr);
			for (HashMap<String,String> map : list) {
				String institutionName = map.get (EducationColumns.INSTITUTION_NAME.getColumnName());
				if (institutionName != null 
						&& !"".equals(institutionName.trim())
						&& institutionName.startsWith (prefix.trim())) {
					String educationId = map.get (EducationColumns.EDUCATION_ID.getColumnName());
					Education education = new Education ();
					education.setEducationId (new Long (educationId));
					education.setInstitutionName (institutionName);
					educationList.add (education);
				}
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred during the findAll operation.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method findByInstitutionNamePrefix.");
		return educationList;
	}

}
