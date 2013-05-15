package com.thesyncme.dao.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thesyncme.business.entities.Role;
import com.thesyncme.dao.RoleDAO;

/**
 * Role DAO Unit Test class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class RoleDAOImplTest extends TestCase {

	private RoleDAO roleDAO;
	private static final boolean SUCCESS_OPERATION = true;
	
	@Before 
	public void setUp() {
	    try {
	    	roleDAO = new RoleDAOImpl ();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	@Test
	public void testFindAll () {
		boolean isFound = false;
		List<Role> roleList = new ArrayList<Role>();
		try {
			roleList = roleDAO.findAll ();
			if (!roleList.isEmpty()) {
				isFound = true;
			}
            assertEquals ("The result for the operation testFindAll [" + isFound + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isFound);
			
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}
	
	@Test
	public void testFindById () {
		boolean isFound = false;
		Role foundRole = null;
		try {
			foundRole = roleDAO.findById ("1");
			if (foundRole != null) {
				isFound = true;
			}
            assertEquals ("The result for the operation testFindById [" + isFound + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isFound);			
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}
	
	@After
	protected void tearDown () {
		roleDAO = null;
	}
	
}
