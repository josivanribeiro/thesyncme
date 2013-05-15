package com.thesyncme.presentation;

import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.jsf.FacesContextUtils;

/**
 * Abstract Base Managed Bean class containing useful methods.
 * 
 * @author Josivan Ribeiro
 *
 */
public abstract class AbstractBaseBean {
	
	/**
	 * Adds an info FacesMessage to the set of messages of the FacesContext.
	 * 
	 * @param message the info message.
	 */
	protected void addInfoMessage (String message) {
		FacesContext.getCurrentInstance().addMessage (null, new FacesMessage (FacesMessage.SEVERITY_INFO, message, message));
	}
	
	/**
	 * Adds an warn FacesMessage to the set of messages of the FacesContext.
	 * 
	 * @param message the warn message.
	 */
	protected void addWarnMessage (String message) {
		FacesContext.getCurrentInstance().addMessage (null, new FacesMessage (FacesMessage.SEVERITY_WARN, message, message));
	}
	
	/**
	 * Adds an error FacesMessage to the set of messages of the FacesContext.
	 * 
	 * @param message the error message.
	 */
	protected void addErrorMessage (String message) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage (FacesMessage.SEVERITY_ERROR, message, message));
	}
	
	/**
	 * Sets a default Locale to the FacesContext.
	 * 
	 * @param locale the default Locale.
	 */
	protected void setDefaultLocale (String locale) {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(locale));		
	}
	
	/**
	 * Returns the language code from the default Locale.
	 * 
	 * @return the language code.
	 */
	protected String getDefaultLanguageCode () {
		return FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
	}
	
	/**
	 * Gets the default Locale instance.
	 * 
	 * @return the default locale instance.
	 */
	protected Locale getDefaultLocale () {
		return FacesContext.getCurrentInstance().getViewRoot().getLocale();
	}
	
	/**
	 * Gets a spring bean from the web application context.
	 * @param clazz the class.
	 * @return the spring bean instance.
	 */
	protected Object getSpringBean (Class clazz) {
		Object springBean = null;
		WebApplicationContext ctx =  FacesContextUtils.getWebApplicationContext (FacesContext.getCurrentInstance());
		springBean = ctx.getBean (clazz);
		return springBean;
	}
	
}
