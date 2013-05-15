package com.thesyncme.dao.impl;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thesyncme.business.entities.User;
import com.thesyncme.business.services.EmailService;
import com.thesyncme.business.services.impl.EmailServiceImpl;

/**
 * Email Service Unit Test class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class EmailServiceImplTest extends TestCase {

	private static final boolean SUCCESS_OPERATION = true;
	private EmailService emailService;
		
	@Before 
	public void setUp () {
		emailService = new EmailServiceImpl ();
    }
	
	@Test
	public void testSendResetPasswordEmail () {
		boolean isSent = false;
		try {
			User user = new User ();
			user.setUsername("Josivan Ribeiro");
			user.setEmail("josivan@gmail.com");
			user.setTokenOperation ("11");
			isSent = emailService.sendResetPasswordEmail (user);
            assertEquals ("The result for the operation testCreate [" + isSent + "] " +
            			  	"is different of the expected [" + SUCCESS_OPERATION + "].", SUCCESS_OPERATION, isSent);
		} catch (Exception e) {
			e.printStackTrace ();
		}
	}
	
	@After
	protected void tearDown () {
		emailService = null;
	}
	
}
