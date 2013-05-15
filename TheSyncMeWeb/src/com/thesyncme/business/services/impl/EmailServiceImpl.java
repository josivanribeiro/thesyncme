package com.thesyncme.business.services.impl;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.thesyncme.business.entities.EmailConfiguration;
import com.thesyncme.business.entities.User;
import com.thesyncme.business.services.EmailService;
import com.thesyncme.constants.Constants;
import com.thesyncme.exceptions.BusinessException;

@Service ("emailService")
public class EmailServiceImpl implements EmailService {
	/**
	 * Default logger.
	 */
	private static Logger logger = Logger.getLogger (EmailServiceImpl.class);
	
	@Override
	public boolean sendResetPasswordEmail (User user) throws BusinessException {
		logger.info ("Start executing the method sendResetPasswordEmail.");
		boolean sent = false;
		EmailConfiguration emailConfig = null;
		String htmlContent = null;
		emailConfig = new EmailConfiguration ();
		emailConfig.setHost (Constants.DEFAULT_SMTP_HOST);
		emailConfig.setPort (Constants.DEFAULT_SMTP_PORT);
		emailConfig.setFrom (Constants.DEFAULT_SENDER);
		emailConfig.setMailer (Constants.DEFAULT_HEADER_MAILER);
		emailConfig.setUser (Constants.DEFAULT_SMTP_AUTH_USER);
		emailConfig.setPassword (Constants.DEFAULT_SMTP_AUTH_PWD);
		emailConfig.setTo (user.getEmail());
		emailConfig.setSubject (Constants.RESET_YOUR_PASSWORD_SUBJECT);
		htmlContent = buildResetUserPasswordHtmlContent (user);
		try {
			sent = sendHtmlEmail (emailConfig, htmlContent);
			logger.info ("sent [" + sent + "]");
		} catch (Exception e) {
			String errorMessage = "A business exception error occurred while sending the Reset password email.";
			logger.error (errorMessage, e);
			throw new BusinessException (errorMessage, e.getCause());
		}
		logger.info ("Finish executing the method sendResetPasswordEmail.");
		return sent;
	}
	
	/**
	 * Sends a HTML email.
	 * 
	 * @param emailConfig the email configuration bean.
	 * @param htmlContent the HTML content.
	 * @return a boolean indicating the success operation.
	 * @throws MessagingException
	 * @throws IOException
	 */
	private boolean sendHtmlEmail (final EmailConfiguration emailConfig, String htmlContent) throws MessagingException, IOException {
		logger.info ("Starting executing the method sendHtmlEmail.");
		boolean sent = false;
		Properties props = System.getProperties ();
	    // XXX - could use Session.getTransport() and Transport.connect()
	    // XXX - assume we're using SMTP
	    props.put("mail.smtp.host", emailConfig.getHost ());	    
	    // begin - further configuration to send email via TLS (e.g., using Gmail SMTP)
	    props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");		
		props.put("mail.smtp.user", emailConfig.getUser());
		props.put("mail.smtp.host", emailConfig.getHost());
		props.put("mail.smtp.port", emailConfig.getPort());
		props.put("mail.smtp.starttls.enable","true");
		props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.socketFactory.port", emailConfig.getPort());
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");		
		// end - further configuration to send email via TLS (e.g., using Gmail SMTP)
	    // Get a Session object
	    Session session = Session.getInstance (props);
	    // construct the message
	    Message msg = new MimeMessage (session);
	    msg.setFrom (new InternetAddress (emailConfig.getFrom()));
	    msg.setRecipients (Message.RecipientType.TO, InternetAddress.parse (emailConfig.getTo(), false));
	    if (emailConfig.getCc() != null) {
	    	msg.setRecipients (Message.RecipientType.CC, InternetAddress.parse (emailConfig.getCc(), false));
	    }
	    if (emailConfig.getBcc() != null) {
	    	msg.setRecipients (Message.RecipientType.BCC, InternetAddress.parse (emailConfig.getBcc(), false));
	    }
	    msg.setSubject (emailConfig.getSubject());
	    msg.setHeader ("X-Mailer", emailConfig.getMailer());
	    msg.setSentDate (new Date());
		msg.setDataHandler (new DataHandler (new ByteArrayDataSource (htmlContent, "text/html")));
		Transport transport = session.getTransport ("smtps");
		transport.connect (emailConfig.getHost(), emailConfig.getPort(), emailConfig.getUser(), emailConfig.getPassword());
		transport.sendMessage(msg, msg.getAllRecipients());
		transport.close ();
		sent = true;
	    logger.info ("emailConfig.getHost() [" + emailConfig.getHost() + "]");
		logger.info ("emailConfig.getFrom() [" + emailConfig.getFrom() + "]");
		logger.info ("emailConfig.getTo() [" + emailConfig.getTo() + "]");
		logger.info ("emailConfig.getSubject() [" + emailConfig.getSubject() + "]");
		logger.info ("emailConfig.getMailer() [" + emailConfig.getMailer() + "]");
	    logger.info ("sent [" + sent + "]");
	    logger.info ("Finish executing the method sendHtmlEmail.");	    
	    return sent;
	}

	/**
	 * Builds HTML content from the Reset user password email.
	 * 
	 * @param user the user used to customize the message.
	 * @return a string representing the new HTML content.
	 */
	private String buildResetUserPasswordHtmlContent (User user) {
		logger.info ("Start executing the method buildResetUserPasswordHtmlContent.");
		logger.info ("user.getUsername() [" + user.getUsername() + "]");
		logger.info ("user.getEmail() [" + user.getEmail() + "]");
		logger.info ("user.getTokenOperation() [" + user.getTokenOperation() + "]");
		StringBuilder sb = new StringBuilder ();
		sb.append ("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>");
		sb.append ("<html xmlns='http://www.w3.org/1999/xhtml'>																				 ");
		sb.append ("	<head>																												 ");		
		sb.append ("		<title>TheSyncMe</title>																						 ");
		sb.append ("		<style>																											 ");
		sb.append ("			/* =Structure																								 ");
		sb.append ("			   ----------------------------------------------- */														 ");				
		sb.append ("			   body {																									 ");
		sb.append ("					padding: 0 2em;																					     ");
		sb.append ("					background: #ccc;																					 ");
		sb.append ("					color: #373737;																					     ");
		sb.append ("					font: 15px 'Helvetica Neue', Helvetica, Arial, sans-serif;											 ");
		sb.append ("					font-weight: 300;																					 ");
		sb.append ("					line-height: 1.625;																					 ");
		sb.append ("			   }																										 ");
		sb.append ("			/* =Global																									 ");
		sb.append ("			   ----------------------------------------------- */														 ");
		sb.append ("			   #page {																									 ");
		sb.append ("					margin: 20px auto;																					 ");
		sb.append ("					max-width: 660px;																					 ");
		sb.append ("					border-radius: 10px 10px 10px 10px;																	 ");
		sb.append ("					background: #fff;																					 ");
		sb.append ("					border-left: 1px solid #ccc;																		 ");
		sb.append ("					border-right: 1px solid #ccc;																		 ");
		sb.append ("					border-bottom: 1px solid #ccc;																		 ");
		sb.append ("				}																										 ");
		sb.append ("			/* Links */																									 ");
		sb.append ("				a {																										 ");
		sb.append ("					color: #12a0ff;																					     ");
		sb.append ("					text-decoration: none;																				 ");
		sb.append ("				}																										 ");
		sb.append ("				a:focus,																								 ");
		sb.append ("				a:active,																								 ");
		sb.append ("				a:hover {																								 ");
		sb.append ("					text-decoration: none;																				 ");
		sb.append ("				}																										 ");
		sb.append ("			/* =Header																									 ");
		sb.append ("			   ----------------------------------------------- */														 ");
		sb.append ("			   #header {																								 ");
		sb.append ("					position: relative;																					 ");
		sb.append ("					border-bottom: 1px solid #ccc;																		 ");
		sb.append ("					padding-bottom: 10px;																				 ");
		sb.append ("					position: relative;																					 ");
		sb.append ("					z-index: 9999;																						 ");
		sb.append ("					height:70px;																						 ");
		sb.append ("				}																										 ");
		sb.append ("				#logo {																									 ");
		sb.append ("					float: left;																						 ");
		sb.append ("					margin: 12px 0 0 10px;																				 ");
		sb.append ("				}																										 ");
		sb.append ("				#logo img {																								 ");
		sb.append ("					height: 57px;																						 ");
		sb.append ("					width: 173px;																						 ");
		sb.append ("					position:absolute;																					 ");
		sb.append ("				}																										 ");
		sb.append ("			/* =Content																									 ");
		sb.append ("			   ----------------------------------------------- */														 ");
		sb.append ("			   #main {																								     ");
		sb.append ("					clear: both;																						 ");
		sb.append ("					height: 325px;																						 ");
		sb.append ("				}																										 ");
		sb.append ("				#title-box p {																							 ");
		sb.append ("					margin: 12px 20px 16px 20px;																		 ");
		sb.append ("					color: #000;																						 ");
		sb.append ("					font-size: 18px;																					 ");
		sb.append ("					font-weight: bold;																					 ");
		sb.append ("					vertical-align: middle;																				 ");	
		sb.append ("				}																										 ");
		sb.append ("				#content-box {																							 ");
		sb.append ("					background-color: #f5f5f5;																			 ");
		sb.append ("					color: #666666;																						 ");
		sb.append ("					padding: 10px 10px 13px 10px;																		 ");
		sb.append ("					margin: 0px 20px;																					 ");
		sb.append ("					font-size: 14px;																					 ");
		sb.append ("					border-top-left-radius: 10px;																		 ");
		sb.append ("					border-top-right-radius: 10px;																		 ");
		sb.append ("				}																										 ");
		sb.append ("				#content-box .paragraph {																				 ");
		sb.append ("					margin-bottom: 10px;																				 ");
		sb.append ("				}																										 ");
		sb.append ("				#email-signature {																						 ");
		sb.append ("					margin: 0px 20px;																					 ");
		sb.append ("					padding: 10px;																						 ");
		sb.append ("					background-color: #f5f5f5;																			 ");
		sb.append ("					border-bottom-right-radius: 10px;																	 ");
		sb.append ("					border-bottom-left-radius: 10px;																	 ");
		sb.append ("					border-top: solid 1px #ccc;																			 ");
		sb.append ("				}																										 ");
		sb.append ("				#email-signature p {																					 ");
		sb.append ("					color: #333;																						 ");
		sb.append ("					font-size: 15px;																					 ");
		sb.append ("				}																										 ");
		sb.append ("		</style>																										 ");
		sb.append ("	</head>																											     ");
		sb.append ("	<body>																												 ");
		sb.append ("		<!-- page -->																									 ");
		sb.append ("		<div id='page'>																									 ");
		sb.append ("			<!-- header -->																							     ");
		sb.append ("			<div id='header'>																							 ");
		sb.append ("				<!-- logo -->																						     ");
		sb.append ("				<h1 id='logo'>																							 ");
		sb.append ("					<a href='http://localhost:8080/thesyncme/login.page'>											     ");
		sb.append ("						<img src='http://localhost:8080/thesyncme/images/logo_thesyncme.gif' alt='TheSyncMe' />			 ");
		sb.append ("					</a>																								 ");
		sb.append ("				</h1>																									 ");
		sb.append ("			</div>																										 ");
		sb.append ("			<!-- main -->																								 ");
		sb.append ("			<div id='main'>																								 ");
		sb.append ("				<div id='title-box'>																					 ");
		sb.append ("					<p>Reset your Password, " + user.getUsername() + "!</p>												 ");
		sb.append ("				</div>																								     ");
		sb.append ("				<div id='content-box'>																				     ");
		sb.append ("					<p class='paragraph'>																			     ");
		sb.append ("						The TheSyncMe was requested to reset your password.												 ");
		sb.append ("					</p>																						         ");
		sb.append ("					<p class='paragraph'>																			     ");
		sb.append ("						If you want to reset it, please click on the link below.										 ");
		sb.append ("					</p>																								 ");
		sb.append ("					<p class='paragraph'>																			     ");
		sb.append ("						<a href='http://localhost:8080/thesyncme/resetUserPassword.page?e=" + user.getEmail() + "&t=" + user.getTokenOperation() + "'>http://localhost:8080/thesyncme/resetUserPassword.page?e=" + user.getEmail() + "&t=" + user.getTokenOperation() + "</a>");
		sb.append ("					</p>																								 ");
		sb.append ("					<p>																									 ");
		sb.append ("						Thanks,																							 ");
		sb.append ("					</p>																								 ");
		sb.append ("				</div>																									 ");
		sb.append ("				<div id='email-signature'>																				 ");
		sb.append ("					<p>TheSyncMe Team</p>																				 ");
		sb.append ("				</div>																									 ");
		sb.append ("			</div>																										 ");
		sb.append ("		</div>																											 ");
		sb.append ("	</body>																												 ");
		sb.append ("</html>																													 ");
		logger.info ("Finish executing the method buildResetUserPasswordHtmlContent.");
		return sb.toString ();
	}

}
