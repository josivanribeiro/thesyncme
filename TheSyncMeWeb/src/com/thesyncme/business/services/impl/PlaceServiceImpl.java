package com.thesyncme.business.services.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.thesyncme.business.entities.Place;
import com.thesyncme.business.services.PlaceService;
import com.thesyncme.dao.impl.PlaceDAOImpl;
import com.thesyncme.exceptions.BusinessException;
import com.thesyncme.exceptions.DataAccessException;

@Service ("placeService")
public class PlaceServiceImpl implements PlaceService {
	/**
	 * Default logger.
	 */
	private static Logger logger = Logger.getLogger (PlaceServiceImpl.class);
	/**
	 * Attribute that defines the Place DAO instance.
	 */
	private PlaceDAOImpl placeDAO;	
	
	public void setPlaceDAO (PlaceDAOImpl placeDAO) {
		this.placeDAO = placeDAO;
	}

	@Override
	public void createPlace (Place place) throws BusinessException {
		logger.info ("Start executing the method createPlace().");
		try {
			placeDAO.create (place);
		} 
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while creating a new place.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method createPlace().");
	}

	@Override
	public void updatePlace (Place place) throws BusinessException {
		logger.info ("Start executing the method updatePlace().");
		try {
			placeDAO.update (place);
		} 
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while updating a place.";
			logger.error (errorMessage, e); 
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method updatePlace().");		
	}
	
	@Override
	public Place findByEmailOrUsername (Place place) throws BusinessException {
		logger.info ("Start executing the method findByEmailOrUsername().");
		Place foundPlace = null;
		try {
			foundPlace = placeDAO.findByEmailOrUsername (place);
		}
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred trying to find the place by email or username.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findByEmailOrUsername().");
		return foundPlace;
	}

	@Override
	public Place findByUsername (String username) throws BusinessException {
		logger.info ("Start executing the method findByUsername().");
		Place foundPlace = null;
		try {
			foundPlace = placeDAO.findByUsername (username);
		}
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred trying to find the place by username.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findByUsername().");
		return foundPlace;
	}

	@Override
	public boolean updatePassword (Place place) throws BusinessException {
		logger.info ("Start executing the method updatePassword().");
		boolean isUpdated = false;
		try {
			isUpdated = placeDAO.updatePassword (place);
		}
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred when trying to update the place password.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method updatePassword().");
		return isUpdated;
	}

	@Override
	public String generateTokenOperation (Place place) throws BusinessException {
		logger.info ("Start executing the method generateTokenOperation().");
		String token = null;
		try {
			token = placeDAO.generateTokenOperation (place);
		}
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred when trying to generate the token operation.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method generateTokenOperation().");
		return token;
	}	

}
