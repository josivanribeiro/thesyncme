package com.thesyncme.jsf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.thesyncme.constants.Constants;

/**
 * Email Validator implementation class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class EmailValidator implements Validator {

	@Override
	public void validate (FacesContext context, UIComponent component, Object value) throws ValidatorException {
		boolean isValidEmail = true;
		String email = null;
		email = (String)value;		
		if (value == null || "".equals(email.trim())) {
			isValidEmail = false;
		} else if (!email.matches (Constants.EMAIL_REGEX)) {
			isValidEmail = false;
		}
		if (!isValidEmail) {
        	String message = (String) component.getAttributes().get("message");
        	FacesMessage facesMessage = new FacesMessage ();
            facesMessage.setDetail (message);
            facesMessage.setSummary (message);
            facesMessage.setSeverity (FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException (facesMessage);
        }
		
	}

}
