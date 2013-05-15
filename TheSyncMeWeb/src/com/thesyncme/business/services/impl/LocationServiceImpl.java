package com.thesyncme.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.thesyncme.business.entities.Location;
import com.thesyncme.business.services.LocationService;
import com.thesyncme.dao.impl.LocationDAOImpl;
import com.thesyncme.exceptions.BusinessException;
import com.thesyncme.exceptions.DataAccessException;

@Service ("locationService")
public class LocationServiceImpl implements LocationService {
	/**
	 * Default logger.
	 */
	private static Logger logger = Logger.getLogger (LocationServiceImpl.class);
	/**
	 * Attribute that defines the Location DAO instance.
	 */
	private LocationDAOImpl locationDAO;
	
	public void setLocationDAO (LocationDAOImpl locationDAO) {
		this.locationDAO = locationDAO;
	}

	@Override
	public List<Location> findAll() throws BusinessException {
		List<Location> locationList = new ArrayList<Location>();
		logger.info ("Start executing the method findAll().");
		try {
			locationList = locationDAO.findAll();			
		}
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding a location list.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findAll().");
		return locationList;
	}
	
	@Override
	public List<Location> findByCityNamePrefix (String cityNamePrefix) throws BusinessException {
		List<Location> locationList = new ArrayList<Location>();
		logger.info ("Start executing the method findByCityNamePrefix().");
		logger.info ("cityNamePrefix [" + cityNamePrefix + "]");
		try {
			locationList = locationDAO.findByCityNamePrefix (cityNamePrefix);			
		}
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding a city by the name prefix.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findByCityNamePrefix().");
		return locationList;
	}		
	
}
