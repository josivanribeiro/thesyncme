package com.thesyncme.dao.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thesyncme.business.entities.Company;
import com.thesyncme.dao.CompanyDAO;

/**
 * Company DAO Unit Test class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class CompanyDAOImplTest extends TestCase {

	private CompanyDAO companyDAO;
	private static final boolean SUCCESS_OPERATION = true;
	
	@Before 
	public void setUp() {
	    try {
	    	companyDAO = new CompanyDAOImpl();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	@Test
	public void testFindAll () {
		boolean isFound = false;
		List<Company> companyList = new ArrayList<Company>();
		try {
			companyList = companyDAO.findAll ();
			if (!companyList.isEmpty()) {
				isFound = true;
			}
            assertEquals ("The result for the operation testFindAll [" + isFound + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isFound);
			
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}
	
	@Test
	public void testFindByCompanyNamePrefix () {
		boolean isFound = false;
		List<Company> companyList = new ArrayList<Company>();
		try {
			String prefix = "I";
			companyList = companyDAO.findByCompanyNamePrefix (prefix);
			if (!companyList.isEmpty()) {
				isFound = true;
			}
            assertEquals ("The result for the operation testFindByCompanyNamePrefix [" + isFound + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isFound);
			
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}
	
	@After
	protected void tearDown () {
		companyDAO = null;
	}
	
}
