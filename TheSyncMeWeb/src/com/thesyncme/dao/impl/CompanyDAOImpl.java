package com.thesyncme.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.thesyncme.business.entities.Company;
import com.thesyncme.constants.Constants;
import com.thesyncme.dao.AbstractDAO;
import com.thesyncme.dao.CompanyDAO;
import com.thesyncme.exceptions.DataAccessException;

@Repository
public class CompanyDAOImpl extends AbstractDAO implements CompanyDAO {
	/**
	 * Default logger.
	 */
	private static Logger logger = Logger.getLogger (CompanyDAOImpl.class);
	/**
	 * Attribute that defines the column family name array.
	 */
	private String[] columnFamilyNameArr = {
		"Company"
	};
	/**
	 * Attribute that defines the column name array.
	 */
	private String[] columnNameArr = {
		CompanyColumns.COMPANY_ID.getColumnName (),
		CompanyColumns.NAME.getColumnName ()
	};
	/**
	 * Education enum columns.
	 *
	 */
	private enum CompanyColumns {
		COMPANY_ID ("companyId"), 
		NAME ("name");
						
		private String columnName;
				
		private CompanyColumns (String columnName) {
			this.columnName = columnName;
		}
		
		public String getColumnName () {
			return columnName;
		}		
	}	
	
	@Override
	public List<Company> findAll() throws DataAccessException {
		logger.info ("Start executing the method findAll.");
		List<Company> companyList = new ArrayList<Company> ();
		ArrayList<HashMap<String, String>> list = null;
		String[][] columnNamesBidimensionalArr = {
			columnNameArr
		};
		try {
			// getting all the records from the Education table
			list = getTable (Constants.COMPANY_TABLENAME, columnFamilyNameArr, columnNamesBidimensionalArr);
			for (HashMap<String,String> map : list) {
				String companyId = map.get (CompanyColumns.COMPANY_ID.getColumnName());
				String name = map.get (CompanyColumns.NAME.getColumnName());		
				Company company = new Company ();
				company.setCompanyId (new Long(companyId));
				company.setName (name);
				companyList.add (company);
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred during the findAll operation.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method findAll.");
		return companyList;
	}

	@Override
	public List<Company> findByCompanyNamePrefix (String companyNamePrefix) throws DataAccessException {
		logger.info ("Start executing the method findByCompanyNamePrefix.");
		List<Company> companyList = new ArrayList<Company> ();
		ArrayList<HashMap<String, String>> list = null;
		String[][] columnNamesBidimensionalArr = {
			columnNameArr
		};
		try {
			// getting all the records from the Company table
			list = getTable (Constants.COMPANY_TABLENAME, columnFamilyNameArr, columnNamesBidimensionalArr);
			for (HashMap<String,String> map : list) {
				String companyName = map.get (CompanyColumns.NAME.getColumnName());
				if (companyName != null && !"".equals (companyName.trim())) {
					if (companyName.startsWith (companyNamePrefix)) {
						String companyId = map.get (CompanyColumns.COMPANY_ID.getColumnName());						
						Company company = new Company ();
						company.setCompanyId (new Long(companyId));
						company.setName (companyName);						
						companyList.add (company);
					}
				}	
			}				
		} catch (IOException e) {
			String errorMessage = "A data access error occurred during the search by company name prefix operation.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method findByCompanyNamePrefix.");
		return companyList;
	}

}
