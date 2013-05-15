package com.thesyncme.dao.impl;

import java.io.IOException;
import java.util.HashMap;
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

import com.thesyncme.business.entities.User;
import com.thesyncme.constants.Constants;
import com.thesyncme.dao.UserDAO;

/**
 * User DAO Unit Test class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class UserDAOImplTest extends TestCase {

	private UserDAO userDAO;
	private Configuration conf = null;
	private HTable table = null;
	private static final boolean SUCCESS_OPERATION = true;
	
	@Before 
	public void setUp() {
	    try {
	    	conf = HBaseConfiguration.create ();
	    	table = new HTable (conf, Constants.USER_TABLENAME);
	    	userDAO = new UserDAOImpl ();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	
	/*@Test
	public void testCreate () {
		boolean isCreated = false;
		try {
			User user = new User ();
			user.setUsername("Josivan Ribeiro da Silva");
			user.setEmail("josivan@gmail.com");
			user.setPassword("j37r15s3");
			Role adminRole = new Role ();
			adminRole.setRoleId (1);
			adminRole.setName("Administrator");
			Role userRole = new Role ();
			userRole.setRoleId (2);
			userRole.setName ("User");
			List<Role> roleList = new ArrayList<Role> ();
			roleList.add (adminRole);
			roleList.add (userRole);
			user.setRoleList (roleList);
			userDAO.create (user);
            String key = user.getUsername();
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
	
	@Test
	public void testUpdate () {
		boolean isUpdated = false;
		try {
			User user = new User ();
			user.setUsername ("Josivan Silva");
			// setting the user company
			Company company = new Company ();
			company.setCompanyId (new Long(1));
			company.setName ("Google");
			
			user.setCompany (company);
			
			// setting the user location
			Location location = new Location();
			location.setLocationId (new Long(1));
			location.setCityName ("Curitiba");
			location.setStateName ("Paraná");
			location.setCountryName ("Brasil");
			
			user.setLocation (location);
			
			// setting the user position
			
			Position position = new Position ();
			position.setPositionId (new Long(1));
			position.setName ("Sr. Software Engineer");
			
			user.setPosition (position);
			
			// setting the user education list
			
			Education education1 = new Education ();
			education1.setEducationId (new Long(1));
			education1.setInstitutionName ("PUCPR");
			
			Education education2 = new Education();
			education2.setEducationId (new Long(2));
			education2.setInstitutionName ("COC");
			
			List<Education> educationList = new ArrayList<Education>();
			educationList.add (education1);
			educationList.add (education2);
			
			user.setEducationList (educationList);
			
			// setting the user language list
			Language language1 = new Language ();
			language1.setLanguageId (1);
			language1.setName("English");
			
			Language language2 = new Language ();
			language2.setLanguageId (2);
			language2.setName("Portuguese");
			
			List<Language> languageList = new ArrayList<Language> ();
			languageList.add (language1);
			languageList.add (language2);
			
			user.setLanguageList (languageList);
			
			userDAO.update (user);
			
			// checking the result
			String rowKey = user.getUsername ();
			// getting the user company
			String[] companyColumnArr = {
				UserColumns.COMPANY_COMPANY_ID.getColumnName()
			};
			Map<String,String> companyIdMap = null;
			companyIdMap = getRowByColumnFamilyAndColumnName (Constants.USER_TABLENAME, rowKey, UserColumnFamilies.COMPANY_COMPANY_ID.getColumnFamilyName(), companyColumnArr);
			// getting the user location
			String[] locationColumnArr = {
				UserColumns.LOCATION_LOCATION_ID.getColumnName()	
			};
			Map<String,String> locationIdMap = null;
			locationIdMap = getRowByColumnFamilyAndColumnName (Constants.USER_TABLENAME, rowKey, UserColumnFamilies.LOCATION_LOCATION_ID.getColumnFamilyName(), locationColumnArr);
			// getting the user position
			String[] positionColumnArr = {
				UserColumns.POSITION_POSITION_ID.getColumnName()	
			};
			Map<String,String> positionIdMap = null;
			positionIdMap = getRowByColumnFamilyAndColumnName (Constants.USER_TABLENAME, rowKey, UserColumnFamilies.POSITION_POSITION_ID.getColumnFamilyName(), positionColumnArr);
			
			// getting the user education
			Map<String,String> educationIdMap = null;
			educationIdMap = getRow (Constants.USER_TABLENAME, rowKey);
			
			// getting the user languages
			Map<String,String> languageIdMap = null;
			languageIdMap = getRow (Constants.USER_TABLENAME, rowKey);
			
			if (companyIdMap != null
					&& locationIdMap != null
					&& positionIdMap != null
					&& educationIdMap != null
					&& languageIdMap != null
					&& companyIdMap.containsValue("1")
					&& locationIdMap.containsValue("1")
					&& positionIdMap.containsValue("1")
					&& educationIdMap.containsValue("1")
					&& educationIdMap.containsValue("2")
					&& languageIdMap.containsValue("1")
					&& languageIdMap.containsValue("2")) {
				isUpdated = true;
			}
			
			assertEquals ("The result for the operation testUpdate [" + isUpdated + "] " +
    			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isUpdated);	
			
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}*/
	
	@Test
	public void testFindByEmail () {
		boolean isUserFound = false;
		User foundUser = null;
		User user = new User ();
		user.setEmail ("josivan@gmail.com");
		try {
			foundUser = userDAO.findByEmail (user);
			if (foundUser != null 
					&& foundUser.getEmail() != null
					&& !"".equals (foundUser.getEmail())
					&& foundUser.getPassword() != null
					&& !"".equals (foundUser.getPassword ())) {
				isUserFound = true;
			}
			assertEquals ("The result for the operation testFindByEmail [" + isUserFound + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isUserFound);
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
	}
	
	/*@Test
	public void testFindByEmailOrUsername () {
		boolean isUserFound = false;
		User foundUser = null;
		User user = new User ();
		user.setEmail ("josivan@gmail.com");
		user.setUsername("Josivan Ribeiro");
		try {
			foundUser = userDAO.findByEmailOrUsername (user);
			if (foundUser != null 
					&& foundUser.getUsername() != null
					&& !"".equals (foundUser.getUsername())) {
				isUserFound = true;
			}
			assertEquals ("The result for the operation testFindByEmailOrUsername [" + isUserFound + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isUserFound);
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
	}
	
	@Test
	public void testFindByUsername () {
		boolean isUserFound = false;
		User foundUser = null;
		String username = "Josivan Silva";
		try {
			foundUser = userDAO.findByUsername (username);
			if (foundUser != null 
					&& foundUser.getUsername() != null
					&& !"".equals (foundUser.getUsername())) {
				isUserFound = true;
			}
			assertEquals ("The result for the operation testFindByUsername [" + isUserFound + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isUserFound);
		}
		catch (Exception e) {
			e.printStackTrace ();
		}
	}
	
	@Test
	public void testGenerateTokenOperation () {
		boolean isGenerated = false;
		String token = null;
		User user = null;
		String username = "Josivan Ribeiro";
		user = new User ();
		user.setUsername (username);
		try {
			token = userDAO.generateTokenOperation (user);
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
		User user = null;
		String username = "Josivan Ribeiro";
		String newPassword = "1234567";
		String tokenOperation = "tk29fba31acc20995c";
		user = new User ();
		user.setUsername (username);
		user.setPassword (newPassword);
		user.setTokenOperation (tokenOperation);
		try {
			isUpdated = userDAO.updatePassword (user);
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
