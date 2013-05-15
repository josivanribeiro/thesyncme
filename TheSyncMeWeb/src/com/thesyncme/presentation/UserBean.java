package com.thesyncme.presentation;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.thesyncme.business.entities.Place;
import com.thesyncme.business.entities.User;
import com.thesyncme.business.services.EmailService;
import com.thesyncme.business.services.PlaceService;
import com.thesyncme.business.services.UserService;
import com.thesyncme.constants.Constants;
import com.thesyncme.exceptions.BusinessException;
import com.thesyncme.helper.BundleHelper;
import com.thesyncme.helper.Helper;

/**
 * Login Managed Bean.
 * 
 * @author Josivan Ribeiro
 *
 */
@Component
public class UserBean extends AbstractBaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger (UserBean.class);
	private String signinEmail;
	private String signinPassword;
	private String signupAccountType = Constants.USER_OR_PLACE_ACCOUNT_TYPE;	
	private String signupUsername;
	private String signupEmail;
	private String signupPassword;	
	private String retrievePasswordEmailOrFullName;	
	private boolean isRemainLogged = false;
	private List<SelectItem> accountTypeOptions = null;
	private User user;
	private Place place;
	private boolean loggedIn = false;
	private UserService userService;
	private PlaceService placeService;
	private EmailService emailService;
				
	@PostConstruct
    public void init() {
		loadAccountTypeOptions ();
		loadUserAuthFromCookies ();
    }

	public String getSigninEmail() {
		return signinEmail;
	}

	public void setSigninEmail(String signinEmail) {
		this.signinEmail = signinEmail;
	}

	public String getSigninPassword() {
		return signinPassword;
	}

	public void setSigninPassword(String signinPassword) {
		this.signinPassword = signinPassword;
	}

	public String getSignupAccountType() {
		return signupAccountType;
	}

	public void setSignupAccountType(String signupAccountType) {
		this.signupAccountType = signupAccountType;
	}

	public String getSignupUsername() {
		return signupUsername;
	}

	public void setSignupUsername(String signupUsername) {
		this.signupUsername = signupUsername;
	}

	public String getSignupEmail() {
		return signupEmail;
	}

	public void setSignupEmail(String signupEmail) {
		this.signupEmail = signupEmail;
	}

	public String getSignupPassword() {
		return signupPassword;
	}

	public void setSignupPassword(String signupPassword) {
		this.signupPassword = signupPassword;
	}

	public boolean isRemainLogged() {
		return isRemainLogged;
	}

	public void setRemainLogged(boolean isRemainLogged) {
		this.isRemainLogged = isRemainLogged;
	}

	public List<SelectItem> getAccountTypeOptions() {
		return accountTypeOptions;
	}

	public void setAccountTypeOptions(List<SelectItem> accountTypeOptions) {
		this.accountTypeOptions = accountTypeOptions;
	}
	
	public String getRetrievePasswordEmailOrFullName() {
		return retrievePasswordEmailOrFullName;
	}

	public void setRetrievePasswordEmailOrFullName (String retrievePasswordEmailOrFullName) {
		this.retrievePasswordEmailOrFullName = retrievePasswordEmailOrFullName;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser (User user) {
		this.user = user;
	}

	public Place getPlace() {
		return place;
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	public void setUserService (UserService userService) {
		this.userService = userService;
	}

	public void setPlaceService (PlaceService placeService) {
		this.placeService = placeService;
	}

	public void setEmailService (EmailService emailService) {
		this.emailService = emailService;
	}

	/**
	 * Sign in the user or place.
	 */
	public String signIn () throws IOException, ServletException {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("/j_spring_security_check");
        dispatcher.forward ((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
        FacesContext.getCurrentInstance().responseComplete ();
        return null;
	}
	
	/**
	 * Sign up a new user or place account.
	 */
	public String signUp () {		
		String toPage = null;
		Locale locale = getDefaultLocale();
		String accountType = this.signupAccountType;
		try {
			if (accountType != null && !"".equals (accountType)) {
				if (accountType.equals (Constants.USER_ACCOUNT_TYPE)) {
					user = new User ();
					user.setUsername (this.signupUsername);
					user.setEmail (this.signupEmail);
					user.setPassword (this.signupPassword);
					userService.createUser (user);
					loggedIn = true;
					toPage = Constants.USER_PLACES_HOME_PAGE;
				} else if (accountType.equals (Constants.PLACE_ACCOUNT_TYPE)) {
					place = new Place ();
					place.setUsername (this.signupUsername);
					place.setEmail (this.signupEmail);
					place.setPassword (this.signupPassword);
					placeService.createPlace (place);
					loggedIn = true;
					toPage = Constants.PLACE_HOME_PAGE;
				}
			}
		}
		catch (BusinessException e) {
			String errorMessage = BundleHelper.getBundlePropertyValue (Constants.LOGIN_SIGNUP_BUSINESS_ERROR, locale);
			logger.error (errorMessage, e);
			addErrorMessage (errorMessage);
		}
		return toPage;
    }
	
	/**
	 * Retrieves the user password.
	 */
	public void retrievePassword () {
		logger.info ("Start executing the method retrievePassword().");
		String message = null;
		this.user = new User ();
		// check whether the user filled field is an email or username
		boolean isEmail = Helper.hasEmailFormat (this.retrievePasswordEmailOrFullName);
		logger.info ("isEmail [" + isEmail + "]");
		logger.info ("this.retrievePasswordEmailOrFullName [" + this.retrievePasswordEmailOrFullName + "]");
		if (isEmail) {
			this.user.setEmail (this.retrievePasswordEmailOrFullName);				
		} else {
			this.user.setUsername (this.retrievePasswordEmailOrFullName);
		}
		try {
			// checking if the user already exists through its email or username
			User foundUser = userService.findByEmailOrUsername (this.user);
			if (foundUser != null 
					&& foundUser.getUsername() != null
					&& !"".equals (foundUser.getUsername())) {
				// generating a new token operation in order to 
				// proceed with the user reset password process
				String newTokenOperation = userService.generateTokenOperation (foundUser);
				if (newTokenOperation != null 
						&& !"".equals (newTokenOperation)) {
					foundUser.setTokenOperation (newTokenOperation);
					boolean isEmailSent = emailService.sendResetPasswordEmail (foundUser);
					if (isEmailSent) {
						message = "An email with password reset instructions was sent to you. Please check it.";
						addInfoMessage (message);
						logger.info ("Reset user password Email sent successfully.");
					}
				}
			} else { // there is no user with the email or username
				message = "There is no for user for the Full name or email address you entered.";
				addInfoMessage (message);
			}
		} catch (BusinessException e) {
			String errorMessage = "An error occured when trying find the user.";
			logger.error (errorMessage, e);
			addErrorMessage (errorMessage);
		}
		logger.info ("Finish executing the method retrievePassword().");
	}
	
	/**
	 * Adds the user auth information into cookies.
	 * 
	 * @param user
	 */
	private void createUserAuthCookies (User user) {
		logger.info("Start executing the method createUserAuthCookies");
		HttpServletResponse response = null;
		if (user != null
				&& user.getEmail() != null
				&& !"".equals (user.getEmail())
				&& user.getPassword() != null
				&& !"".equals (user.getPassword())) {
			response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
			Cookie emailCookie = new Cookie (Constants.USER_EMAIL_AUTH_COOKIE, user.getEmail());
			Cookie passwordCookie = new Cookie (Constants.USER_PASSWORD_AUTH_COOKIE, user.getPassword());
			int maxAge = (365 * 24 * 60 * 60);
			emailCookie.setMaxAge (maxAge);
			passwordCookie.setMaxAge (maxAge);
			response.addCookie (emailCookie);
			response.addCookie (passwordCookie);			
			logger.info("The user with email [" + user.getEmail() + "] has been successfully added into cookies.");
		}
		logger.info("Finish executing the method createUserAuthCookies");
	}
	
	/**
	 * Gets the user auth information from the cookies.
	 * 
	 * @return a new User object.
	 */
	private User getUserFromCookies () {
		logger.info("Start executing the method getUserFromCookies");
		User user = null;
		String email = null;
		String password = null;
		HttpServletRequest httpServletRequest = null;
		httpServletRequest = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Cookie[] cookies = httpServletRequest.getCookies();
		if (cookies != null) {
		   for (int i = 0; i < cookies.length; i++) {
		    Cookie cookie = cookies[i];
			   if (cookie.getName().equalsIgnoreCase (Constants.USER_EMAIL_AUTH_COOKIE)) {
				   email = cookie.getValue();
			   } else if (cookies[i].getName().equalsIgnoreCase (Constants.USER_PASSWORD_AUTH_COOKIE)) {
				   password = cookie.getValue();
			   }
		   }
		   if (email != null 
				   && !"".equals(email)
				   && password != null
				   && !"".equals (password)) {
			   user = new User();
			   user.setEmail (email);
			   user.setPassword (password);
			   logger.info("The user with email [" + user.getEmail() + "] has been successfully retrieved from the cookies.");
		   }
		}
		logger.info("Finish executing the method getUserFromCookies");
		return user;
	}
	
	/**
	 * Loads the User account type options.
	 */
	private void loadAccountTypeOptions () {
		accountTypeOptions = new ArrayList<SelectItem>();
		Locale locale = getDefaultLocale ();
		String defaultAccountTypeLabel = BundleHelper.getBundlePropertyValue (Constants.LOGIN_SIGNUP_ACCOUNT_TYPE_PROPERTY, locale);
		String userLabel = BundleHelper.getBundlePropertyValue (Constants.LOGIN_SIGNUP_ACCOUNT_TYPE_USER_PROPERTY, locale);
		String placeLabel = BundleHelper.getBundlePropertyValue (Constants.LOGIN_SIGNUP_ACCOUNT_TYPE_PLACE_PROPERTY, locale);		
		accountTypeOptions.add (new SelectItem (Constants.USER_OR_PLACE_ACCOUNT_TYPE, defaultAccountTypeLabel));
		accountTypeOptions.add (new SelectItem (Constants.USER_ACCOUNT_TYPE, userLabel));
		accountTypeOptions.add (new SelectItem (Constants.PLACE_ACCOUNT_TYPE, placeLabel));
	}
	
	/**
	 * Loads the User auth information from the cookies.
	 */
	private void loadUserAuthFromCookies () {
		user = getUserFromCookies ();
		if (user != null
				&& user.getEmail() != null
				&& !"".equals (user.getEmail())
				&& user.getPassword() != null
				&& !"".equals (user.getPassword())) {
			this.signinEmail = user.getEmail ();
			this.signinPassword = user.getPassword ();
			this.isRemainLogged = true;
		}		
	}	

}
