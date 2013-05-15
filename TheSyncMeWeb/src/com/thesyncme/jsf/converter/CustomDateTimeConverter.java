package com.thesyncme.jsf.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.thesyncme.helper.Helper;

/**
 * Custom DateTime Converter implementation class.
 * 
 * @author Josivan Ribeiro
 *
 */
public class CustomDateTimeConverter implements Converter {

	@Override
	public Object getAsObject (FacesContext context, UIComponent component, String value) {
		String formattedDate = null;
		Locale locale = null;
		DateFormat df = null;
		Date date = null;
		if (value == null || value.trim().length() == 0) {
			return null;
		}
		try {
			df = DateFormat.getInstance ();
			date = df.parse (value);
			locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
			formattedDate = Helper.formatDate (date, locale);			
		} catch (ParseException e) {
			throw new ConverterException (e);
		}
		return formattedDate;
	}

	@Override
	public String getAsString (FacesContext context, UIComponent component, Object value) {
		String stringValue = null;
		Date date = null;
		Locale locale = null;
		if (value == null) {
			return "";
		}
		locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		date = (Date)value;
		stringValue = Helper.formatDate (date, locale);
		return stringValue;
	}

}
 