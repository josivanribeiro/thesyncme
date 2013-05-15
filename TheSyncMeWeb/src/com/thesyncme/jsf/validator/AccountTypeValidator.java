package com.thesyncme.jsf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import com.thesyncme.constants.Constants;

/**
 * Account Type Validator implementation class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class AccountTypeValidator implements Validator {

	@Override
	public void validate (FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (value == null 
				|| "".equals (value.toString().trim())
				|| (!value.toString().trim().equals (Constants.PLACE_ACCOUNT_TYPE)
						&& !value.toString().trim().equals (Constants.USER_ACCOUNT_TYPE))) {
	        FacesMessage facesMessage = new FacesMessage();
	        String message = (String) component.getAttributes().get("message");
	        facesMessage.setDetail (message);
	        facesMessage.setSummary (message);
	        facesMessage.setSeverity (FacesMessage.SEVERITY_ERROR);
	        throw new ValidatorException (facesMessage);
	    }
	}

}
