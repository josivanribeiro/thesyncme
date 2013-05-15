package com.thesyncme.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.thesyncme.business.entities.Position;
import com.thesyncme.business.services.PositionService;
import com.thesyncme.dao.impl.PositionDAOImpl;
import com.thesyncme.exceptions.BusinessException;
import com.thesyncme.exceptions.DataAccessException;

@Service ("positionService")
public class PositionServiceImpl implements PositionService {
	/**
	 * Default logger.
	 */
	private static Logger logger = Logger.getLogger (PositionServiceImpl.class);
	/**
	 * Attribute that defines the Position DAO instance.
	 */
	private PositionDAOImpl positionDAO;
	
	public void setPositionDAO (PositionDAOImpl positionDAO) {
		this.positionDAO = positionDAO;
	}

	@Override
	public List<Position> findAll () throws BusinessException {
		List<Position> positionList = new ArrayList<Position>();
		logger.info ("Start executing the method findAll().");
		try {
			positionList = positionDAO.findAll();			
		}
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding a position list.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findAll().");
		return positionList;
	}

	@Override
	public List<Position> findByPositionNamePrefix (String positionNamePrefix) throws BusinessException {
		List<Position> positionList = new ArrayList<Position>();
		logger.info ("Start executing the method findByPositionNamePrefix().");
		logger.info ("positionNamePrefix [" + positionNamePrefix + "]");
		try {
			positionList = positionDAO.findByPositionNamePrefix (positionNamePrefix);
		}
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while finding a position by its name prefix.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findByCompanyNamePrefix().");
		return positionList;
	}

}
