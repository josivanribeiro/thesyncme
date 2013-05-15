package com.thesyncme.helper;

import java.util.Locale;
import java.util.ResourceBundle;

import com.thesyncme.constants.Constants;

/**
 * Resource bundle helper class containing useful methods.
 * 
 * @author Josivan Ribeiro
 *
 */
public class BundleHelper {

	/**
	 * Returns a bundle property value given its property name.
	 * 
	 * @param propertyName the property name.
	 * @param locale the default locale. 
	 * @return the bundle property value.
	 */
	public static String getBundlePropertyValue (String propertyName, Locale locale) {
		String value = null;
		String bundlename = Constants.RESOURCE_BUNDLE_BASE_NAME;
		ResourceBundle bundle = ResourceBundle.getBundle (bundlename, locale);
		value = bundle.getString (propertyName);
		return value;
	}	
	
}
