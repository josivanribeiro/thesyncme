package com.thesyncme.jsf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Required Validator implementation class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class RequiredValidator implements Validator {

	@Override
	public void validate (FacesContext context, UIComponent component, Object value) throws ValidatorException {
	    if (value == null || "".equals (value.toString().trim())) {
	        FacesMessage facesMessage = new FacesMessage();
	        String message = (String) component.getAttributes().get("message");
	        facesMessage.setDetail (message);
	        facesMessage.setSummary (message);
	        facesMessage.setSeverity (FacesMessage.SEVERITY_ERROR);
	        throw new ValidatorException (facesMessage);
	    }
	}

}
