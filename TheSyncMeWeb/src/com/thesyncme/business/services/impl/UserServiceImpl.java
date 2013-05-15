package com.thesyncme.business.services.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.thesyncme.business.entities.User;
import com.thesyncme.business.services.UserService;
import com.thesyncme.dao.impl.UserDAOImpl;
import com.thesyncme.exceptions.BusinessException;
import com.thesyncme.exceptions.DataAccessException;

@Service ("userService")
public class UserServiceImpl implements UserService {
	/**
	 * Default logger.
	 */
	private static Logger logger = Logger.getLogger (UserServiceImpl.class);
	/**
	 * Attribute that defines the User DAO instance.
	 */
	private UserDAOImpl userDAO;	
	
	public void setUserDAO (UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public void createUser (User user) throws BusinessException {
		logger.info ("Start executing the method createUser().");
		try {
			userDAO.create (user);
		} 
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while creating a new user.";
			logger.error (errorMessage, e); 
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method createUser().");
	}
	
	@Override
	public void updateUser (User user) throws BusinessException {
		logger.info ("Start executing the method updateUser().");
		try {
			userDAO.update (user);
		} 
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred while updating a user.";
			logger.error (errorMessage, e); 
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method updateUser().");
		
	}

	@Override
	public User findByEmailOrUsername (User user) throws BusinessException {
		logger.info ("Start executing the method findByEmailOrUsername().");
		User foundUser = null;
		try {
			foundUser = userDAO.findByEmailOrUsername (user);
		}
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred trying to find the user by email or username.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findByEmailOrUsername().");
		return foundUser;
	}
	
	@Override
	public User findByUsername (String username) throws BusinessException {
		logger.info ("Start executing the method findByUsername().");
		User foundUser = null;
		try {
			foundUser = userDAO.findByUsername (username);
		}
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred trying to find the user by username.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method findByUsername().");
		return foundUser;
	}

	@Override
	public boolean updatePassword (User user) throws BusinessException {
		logger.info ("Start executing the method updatePassword().");
		boolean isUpdated = false;
		try {
			isUpdated = userDAO.updatePassword (user);
		}
		catch (DataAccessException e) {
			String errorMessage = "A business exception error occurred when trying to update the user password.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method updatePassword().");
		return isUpdated;
	}

	@Override
	public String generateTokenOperation (User user) throws BusinessException {
		logger.info ("Start executing the method generateTokenOperation().");
		String token = null;
		try {
			token = userDAO.generateTokenOperation (user);
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
