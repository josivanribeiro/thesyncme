package com.thesyncme.dao.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.thesyncme.business.entities.Location;
import com.thesyncme.constants.Constants;
import com.thesyncme.dao.AbstractDAO;
import com.thesyncme.dao.LocationDAO;
import com.thesyncme.exceptions.DataAccessException;

@Repository
public class LocationDAOImpl extends AbstractDAO implements LocationDAO {
	/**
	 * Default logger.
	 */
	private static Logger logger = Logger.getLogger (LocationDAOImpl.class);
	/**
	 * Attribute that defines the column family name array.
	 */
	private String[] columnFamilyNameArr = {
		"Location"
	};
	/**
	 * Attribute that defines the column name array.
	 */
	private String[] columnNameArr = {
		LocationColumns.LOCATION_ID.getColumnName (),
		LocationColumns.CITY_NAME.getColumnName(),
		LocationColumns.STATE_NAME.getColumnName(),
		LocationColumns.COUNTRY_NAME.getColumnName()		
	};
	/**
	 * Location enum columns.
	 *
	 */
	private enum LocationColumns {
		LOCATION_ID ("locationId"), 
		CITY_NAME("cityName"), 
		STATE_NAME("stateName"), 
		COUNTRY_NAME("countryName"); 
				
		private String columnName;
				
		private LocationColumns (String columnName) {
			this.columnName = columnName;
		}
		
		public String getColumnName () {
			return columnName;
		}		
	}
		
	@Override
	public List<Location> findByCityNamePrefix (String cityNamePrefix) throws DataAccessException {
		logger.info ("Start executing the method findByCityNamePrefix.");
		List<Location> locationList = new ArrayList<Location> ();
		ArrayList<HashMap<String, String>> list = null;
		String[][] columnNamesBidimensionalArr = {
			columnNameArr
		};
		try {
			// getting all the records from the Location table
			list = getTable (Constants.LOCATION_TABLENAME, columnFamilyNameArr, columnNamesBidimensionalArr);
			for (HashMap<String,String> map : list) {
				String cityName = map.get (LocationColumns.CITY_NAME.getColumnName());
				if (cityName != null && !"".equals (cityName.trim())) {
					if (cityName.startsWith (cityNamePrefix)) {
						String locationId = map.get (LocationColumns.LOCATION_ID.getColumnName());
						String stateName = map.get (LocationColumns.STATE_NAME.getColumnName());
						String countryName = map.get (LocationColumns.COUNTRY_NAME.getColumnName());
						Location location = new Location ();
						location.setLocationId (new Long(locationId));
						location.setCityName (cityName);
						location.setStateName (stateName);
						location.setCountryName (countryName);
						locationList.add (location);
					}
				}			
			}						
		} catch (IOException e) {
			String errorMessage = "A data access error occurred during the find by city name prefix operation.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method findByCityNamePrefix.");
		return locationList;
	}

	@Override
	public List<Location> findAll() throws DataAccessException {
		logger.info ("Start executing the method findAll.");
		List<Location> locationList = new ArrayList<Location> ();
		ArrayList<HashMap<String, String>> list = null;
		String[][] columnNamesBidimensionalArr = {
			columnNameArr
		};
		try {
			// getting all the records from the Location table
			list = getTable (Constants.LOCATION_TABLENAME, columnFamilyNameArr, columnNamesBidimensionalArr);
			for (HashMap<String,String> map : list) {
				String locationId = map.get (LocationColumns.LOCATION_ID.getColumnName());
				String cityName = map.get (LocationColumns.CITY_NAME.getColumnName());		
				String stateName = map.get (LocationColumns.STATE_NAME.getColumnName());
				String countryName = map.get (LocationColumns.COUNTRY_NAME.getColumnName());
				Location location = new Location ();
				location.setLocationId (new Long(locationId));
				location.setCityName (cityName);
				location.setStateName (stateName);
				location.setCountryName (countryName);
				locationList.add (location);
			}
		} catch (IOException e) {
			String errorMessage = "A data access error occurred during the find by city name prefix operation.";
			logger.error (errorMessage);
			throw new DataAccessException (errorMessage, e);
		}
		logger.info ("Finish executing the method findAll.");
		return locationList;
	}

}
