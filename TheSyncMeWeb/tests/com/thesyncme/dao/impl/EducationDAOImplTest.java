package com.thesyncme.dao.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thesyncme.business.entities.Education;
import com.thesyncme.dao.EducationDAO;

/**
 * Education DAO Unit Test class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class EducationDAOImplTest extends TestCase {

	private EducationDAO educationDAO;
	private static final boolean SUCCESS_OPERATION = true;
	
	@Before 
	public void setUp() {
	    try {
	    	educationDAO = new EducationDAOImpl ();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	@Test
	public void testFindAll () {
		boolean isFound = false;
		List<Education> educationList = new ArrayList<Education>();
		try {
			educationList = educationDAO.findAll ();
			if (!educationList.isEmpty()) {
				isFound = true;
			}
            assertEquals ("The result for the operation testFindAll [" + isFound + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isFound);
			
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}
	
	@Test
	public void testFindByInstitutionNamePrefix () {
		boolean isFound = false;
		List<Education> educationList = new ArrayList<Education>();
		try {
			String prefix = "P";
			educationList = educationDAO.findByInstitutionNamePrefix (prefix);
			if (!educationList.isEmpty()) {
				isFound = true;
			}
            assertEquals ("The result for the operation testFindByInstitutionNamePrefix [" + isFound + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isFound);
			
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}
	
	@After
	protected void tearDown () {
		educationDAO = null;
	}
	
}
