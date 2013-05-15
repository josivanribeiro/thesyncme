package com.thesyncme.dao.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thesyncme.business.entities.Location;
import com.thesyncme.dao.LocationDAO;

/**
 * Location DAO Unit Test class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class LocationDAOImplTest extends TestCase {

	private LocationDAO locationDAO;
	private static final boolean SUCCESS_OPERATION = true;
	
	@Before 
	public void setUp() {
	    try {
	    	locationDAO = new LocationDAOImpl ();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	@Test
	public void testFindAll () {
		boolean isFound = false;
		List<Location> locationList = new ArrayList<Location>();
		try {
			locationList = locationDAO.findAll ();
			if (!locationList.isEmpty()) {
				isFound = true;
			}
            assertEquals ("The result for the operation testFindAll [" + isFound + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isFound);
			
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}
	
	@Test
	public void testFindByCityNamePrefix () {
		boolean isFound = false;
		List<Location> locationList = new ArrayList<Location>();
		try {
			String prefix = "C";
			locationList = locationDAO.findByCityNamePrefix (prefix);
			if (!locationList.isEmpty()) {
				isFound = true;
			}
            assertEquals ("The result for the operation testFindByCityNamePrefix [" + isFound + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isFound);
			
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}
	
	@After
	protected void tearDown () {
		locationDAO = null;
	}
	
}
