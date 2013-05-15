package com.thesyncme.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thesyncme.business.entities.Address;
import com.thesyncme.business.entities.Location;
import com.thesyncme.business.entities.Place;
import com.thesyncme.business.entities.Role;
import com.thesyncme.constants.Constants;
import com.thesyncme.dao.PlaceDAO;
import com.thesyncme.enums.PlaceColumnFamilies;
import com.thesyncme.enums.PlaceColumns;

/**
 * Place DAO Unit Test class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class PlaceDAOImplTest extends TestCase {

	private PlaceDAO placeDAO;
	private Configuration conf = null;
	private HTable table = null;
	private static final boolean SUCCESS_OPERATION = true;
	
	@Before 
	public void setUp() {
	    try {
	    	conf = HBaseConfiguration.create ();
	    	table = new HTable (conf, Constants.PLACE_TABLENAME);
	    	placeDAO = new PlaceDAOImpl ();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	@Test
	public void testCreate () {
		boolean isCreated = false;
		try {
			Place place = new Place ();
			place.setUsername("On the Fly Bar");
			place.setEmail("contact@ontheflybar.com");
			place.setPassword("test123");
			Role adminRole = new Role ();
			adminRole.setRoleId (1);
			adminRole.setName("ROLE_ADMIN");
			Role userRole = new Role ();
			userRole.setRoleId (2);
			userRole.setName ("ROLE_USER");
			List<Role> roleList = new ArrayList<Role> ();
			roleList.add (adminRole);
			roleList.add (userRole);
			place.setRoleList (roleList);
			placeDAO.create (place);
            String key = place.getUsername();
            Get get = new Get (Bytes.toBytes(key));
            Result result = table.get (get);
            System.out.println("Result: " + result);
            if (result != null) {
            	isCreated = true;
            }
            assertEquals ("The result for the operation testCreate [" + isCreated + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isCreated);
			
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}
	
	/*@Test
	public void testUpdate () {
		boolean isUpdated = false;
		try {
			Place place = new Place ();
			place.setUsername ("On the Fly Bar");
			// setting the place info
			place.setAbout ("O On the Fly Bar é um tributo ao rock e à cultura pop.");
			place.setWebsite ("http://www.ontheflybar.com");
			// setting the place location
			Location location = new Location();
			location.setLocationId (new Long(1));
			location.setCityName ("Curitiba");
			location.setStateName ("Paraná");
			location.setCountryName ("Brasil");
			place.setLocation (location);
			// setting the place address
			Address address = new Address ();
			address.setAddressId (new Long(1));
			address.setDistrict ("Centro");
			address.setNumber ("200");
			address.setStreet ("Rua Treze de Maio");
			address.setZipCode ("80020-270");
			place.setAddress (address);
			// setting the place phones
			String phone1 = "(41) 3030-6050";
			String phone2 = "(41) 3030-6051";
			List<String> phoneList = new ArrayList<String>();
			phoneList.add (phone1);
			phoneList.add (phone2);
			place.setPhoneList (phoneList);
			placeDAO.update (place);			
			// checking the result
			String rowKey = place.getUsername ();
			// getting the place info about
			String[] aboutColumnArr = {
				PlaceColumns.INFO_ABOUT.getColumnName()
			};
			Map<String,String> aboutMap = null;
			aboutMap = getRowByColumnFamilyAndColumnName (Constants.PLACE_TABLENAME, rowKey, PlaceColumnFamilies.INFO.getColumnFamilyName(), aboutColumnArr);
			// getting the place info website
			String[] websiteColumnArr = {
				PlaceColumns.INFO_WEBSITE.getColumnName()
			};
			Map<String,String> websiteMap = null;
			websiteMap = getRowByColumnFamilyAndColumnName (Constants.PLACE_TABLENAME, rowKey, PlaceColumnFamilies.INFO.getColumnFamilyName(), websiteColumnArr);
			
			// getting the place location
			String[] locationColumnArr = {
				PlaceColumns.LOCATION_LOCATION_ID.getColumnName()	
			};
			Map<String,String> locationIdMap = null;
			locationIdMap = getRowByColumnFamilyAndColumnName (Constants.PLACE_TABLENAME, rowKey, PlaceColumnFamilies.LOCATION_LOCATION_ID.getColumnFamilyName(), locationColumnArr);
			// getting the place address
			String[] addressColumnArr = {
				PlaceColumns.ADDRESS_ADDRESS_ID.getColumnName()	
			};
			Map<String,String> addressIdMap = null;
			addressIdMap = getRowByColumnFamilyAndColumnName (Constants.PLACE_TABLENAME, rowKey, PlaceColumnFamilies.ADDRESS_ADDRESS_ID.getColumnFamilyName(), addressColumnArr);
			// getting the place phone list
			Map<String,String> phoneMap = null;
			phoneMap = getRow (Constants.PLACE_TABLENAME, rowKey);
			if (aboutMap != null
					&& websiteMap != null
					&& locationIdMap != null
					&& addressIdMap != null
					&& phoneMap != null
					&& aboutMap.containsValue("O On the Fly Bar é um tributo ao rock e à cultura pop.")
					&& websiteMap.containsValue("http://www.ontheflybar.com")
					&& locationIdMap.containsValue("1")
					&& addressIdMap.containsValue("1")
					&& phoneMap.containsValue("(41) 3030-6050")
					&& phoneMap.containsValue("(41) 3030-6051")) {
				isUpdated = true;
			}			
			assertEquals ("The result for the operation testUpdate [" + isUpdated + "] " +
    			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isUpdated);	
			
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}
	
	@Test
	public void testFindByEmailOrUsername () {
		boolean isPlaceFound = false;
		Place foundPlace = null;
		Place place = new Place ();
		place.setEmail ("contact@ontheflybar.com");
		place.setUsername("On The Fly Bar");
		try {
			foundPlace = placeDAO.findByEmailOrUsername (place);
			if (foundPlace != null 
					&& foundPlace.getUsername() != null
					&& !"".equals (foundPlace.getUsername())) {
				isPlaceFound = true;
			}
			assertEquals ("The result for the operation testFindByEmailOrUsername [" + isPlaceFound + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isPlaceFound);
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
	}
	
	@Test
	public void testFindByUsername () {
		boolean isPlaceFound = false;
		Place foundPlace = null;
		String username = "On the Fly Bar";
		try {
			foundPlace = placeDAO.findByUsername (username);
			if (foundPlace != null 
					&& foundPlace.getUsername() != null
					&& !"".equals (foundPlace.getUsername())) {
				isPlaceFound = true;
			}
			assertEquals ("The result for the operation testFindByUsername [" + isPlaceFound + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isPlaceFound);
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
	}
	
	@Test
	public void testGenerateTokenOperation () {
		boolean isGenerated = false;
		String token = null;
		Place place = null;
		String username = "On the Fly Bar";
		place = new Place ();
		place.setUsername (username);
		try {
			token = placeDAO.generateTokenOperation (place);
			if (token != null
					&& !"".equals (token)) {
				isGenerated = true;
			}
			assertEquals ("The result for the operation testGenerateTokenOperation [" + isGenerated + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isGenerated);
		}
		catch (Exception e) {
			e.printStackTrace ();
		}		
	}
	
	@Test
	public void testUpdatePassword () {
		boolean isUpdated = false;
		Place place = null;
		String username = "On the Fly Bar";
		String newPassword = "1234567";
		String tokenOperation = "tk0d277a62292c8f52";
		place = new Place ();
		place.setUsername (username);
		place.setPassword (newPassword);
		place.setTokenOperation (tokenOperation);
		try {
			isUpdated = placeDAO.updatePassword (place);
			assertEquals ("The result for the operation testUpdatePassword [" + isUpdated + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isUpdated);
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
	}*/
	
	private Map<String,String> getRowByColumnFamilyAndColumnName (String tableName, String rowKey, String columnFamilyName, String[] columnNameArr) throws IOException {
		Map<String,String> mapResult = new HashMap<String,String>();
		HTable table = new HTable (conf, tableName);
		byte[] rowKeyArr = Bytes.toBytes (rowKey);
		Get getRowData = new Get (rowKeyArr);
        Result res = table.get (getRowData);
        if (res != null && !res.isEmpty()) {
        	for (int i = 0; i < columnNameArr.length ; i++) {
            	String columnName = columnNameArr [i];
            	byte[] obtainedRow = res.getValue (Bytes.toBytes (columnFamilyName), Bytes.toBytes (columnName));
            	String value = Bytes.toString (obtainedRow);
            	mapResult.put (columnName, value);
            }
        }
        return mapResult;
	}
	
	private Map<String,String> getRow (String tableName, String rowKey) throws IOException {
		Map<String,String> mapResult = new HashMap<String,String>();
		HTable table = new HTable (conf, tableName);
		byte[] rowKeyArr = Bytes.toBytes (rowKey);
		Get getRowData = new Get (rowKeyArr);
        Result res = table.get (getRowData);
        if (res != null && !res.isEmpty()) {
        	KeyValue[] keyValueArr = res.raw();
        	for (KeyValue keyValue : keyValueArr) {
        		byte[] obtainedKey = keyValue.getKey ();
        		byte[] obtainedValue = keyValue.getValue ();
				String key = Bytes.toString (obtainedKey);
				String value = Bytes.toString (obtainedValue);
	        	mapResult.put (key, value);
			}
        }
        return mapResult;
	}
	
	@After
	protected void tearDown () {
		conf = null;
		table = null;
	}
	
}
