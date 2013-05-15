package com.thesyncme.business.services;

import com.thesyncme.business.entities.User;
import com.thesyncme.exceptions.BusinessException;

/**
 * Email business service interface.
 * 
 * @author Josivan Ribeiro
 *
 */
public interface EmailService {

	/**
	 * Sends the Reset Password email to the specified user.
	 * 
	 * @param user the user.
	 * @return a boolean indicating the success operation.
	 * @throws BusinessException
	 */
	public boolean sendResetPasswordEmail (User user) throws BusinessException;
	
}
