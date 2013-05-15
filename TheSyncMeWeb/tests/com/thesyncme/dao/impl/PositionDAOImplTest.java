package com.thesyncme.dao.impl;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thesyncme.business.entities.Position;
import com.thesyncme.dao.PositionDAO;

/**
 * Position DAO Unit Test class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class PositionDAOImplTest extends TestCase {

	private PositionDAO positionDAO;
	private static final boolean SUCCESS_OPERATION = true;
	
	@Before 
	public void setUp() {
	    try {
	    	positionDAO = new PositionDAOImpl ();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	@Test
	public void testFindAll () {
		boolean isFound = false;
		List<Position> positionList = new ArrayList<Position>();
		try {
			positionList = positionDAO.findAll ();
			if (!positionList.isEmpty()) {
				isFound = true;
			}
            assertEquals ("The result for the operation testFindAll [" + isFound + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isFound);
			
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}
	
	@Test
	public void testFindByPositionNamePrefix () {
		boolean isFound = false;
		List<Position> positionList = new ArrayList<Position>();
		try {
			String prefix = "S";
			positionList = positionDAO.findByPositionNamePrefix (prefix);
			if (!positionList.isEmpty()) {
				isFound = true;
			}
            assertEquals ("The result for the operation testFindByPositionNamePrefix [" + isFound + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isFound);
			
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}
	
	@After
	protected void tearDown () {
		positionDAO = null;
	}
	
}
