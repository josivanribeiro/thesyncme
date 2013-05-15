package com.thesyncme.dao.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thesyncme.business.entities.Language;
import com.thesyncme.dao.LanguageDAO;

/**
 * Language DAO Unit Test class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class LanguageDAOImplTest extends TestCase {

	private LanguageDAO languageDAO;
	private static final boolean SUCCESS_OPERATION = true;
	
	@Before 
	public void setUp() {
	    try {
	    	languageDAO = new LanguageDAOImpl ();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	@Test
	public void testFindAll () {
		boolean isFound = false;
		List<Language> languageList = new ArrayList<Language>();
		try {
			languageList = languageDAO.findAll ();
			if (!languageList.isEmpty()) {
				isFound = true;
			}
            assertEquals ("The result for the operation testFindAll [" + isFound + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isFound);
			
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}
	
	@Test
	public void testFindByLanguageNamePrefix () {
		boolean isFound = false;
		List<Language> languageList = new ArrayList<Language>();
		try {
			String prefix = "E";
			languageList = languageDAO.findByLanguageNamePrefix (prefix);
			if (!languageList.isEmpty()) {
				isFound = true;
			}
            assertEquals ("The result for the operation testFindByLanguageNamePrefix [" + isFound + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isFound);
			
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}
	
	@After
	protected void tearDown () {
		languageDAO = null;
	}
	
}
