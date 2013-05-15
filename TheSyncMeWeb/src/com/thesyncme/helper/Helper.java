package com.thesyncme.helper;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.thesyncme.business.entities.Location;
import com.thesyncme.constants.Constants;
import com.thesyncme.enums.Roles;

/**
 * Helper class containing useful methods.
 * 
 * @author Josivan Ribeiro
 *
 */
public class Helper {

	/**
	 * Checks if a given string follows the email format.
	 * 
	 * @param s the string.
	 * @return a boolean indicating if the string follows or not the email format.
	 */
	public static boolean hasEmailFormat (String s) {
		return s.matches (Constants.EMAIL_REGEX);
	}
	
	/**
	 * Formats the date according with the locale.
	 * 
	 * @param date the date.
	 * @param locale the locale.
	 * @return a string representing the formatted date.
	 */
	public static String formatDate (Date date, Locale locale) {
		StringBuilder sb = new StringBuilder ();
		Format dayMonthFormatter = null;
		Format dayFormatter = null;
		Format monthFormatter = null;
		Format timeFormatter = null;
		Format yearFormatter = null;
		String at = null;
		if (locale != null) {
			at = BundleHelper.getBundlePropertyValue (Constants.DATE_FORMATTING_AT_PROPERTY, locale);
			if (locale.getLanguage().equals (Locale.US.getLanguage())) {
				dayMonthFormatter = new SimpleDateFormat ("MMMM, d, yy", locale);
				timeFormatter = new SimpleDateFormat ("hh:mm aa", locale);
				sb.append (dayMonthFormatter.format (date));
				sb.append (" " + at + " ");
			    sb.append (timeFormatter.format (date));
			} else if (locale.getLanguage().equals (Constants.BRAZIL.getLanguage())) {
				dayFormatter = new SimpleDateFormat ("d");
				monthFormatter = new SimpleDateFormat ("MMMM", locale);
				timeFormatter = new SimpleDateFormat ("hh:mm", locale);
				yearFormatter = new SimpleDateFormat ("yy");
				sb.append (dayFormatter.format (date));
			    String from = BundleHelper.getBundlePropertyValue (Constants.DATE_FORMATTING_FROM_PROPERTY, locale);
				sb.append (" " + from + " ");
			    sb.append (monthFormatter.format (date));
			    sb.append (" " + from + " ");
			    sb.append (yearFormatter.format (date));
			    sb.append (" " + at + " ");
			    sb.append (timeFormatter.format (date));
			}
		}
	    return sb.toString ();
	}
	
	/**
	 * Creates a list of string containing the location name formatted.
	 * 
	 * @param locationList the list location.
	 * @return list of string with the formatted location name.
	 */
	public static List<String> formatLocationName (List<Location> locationList) {
		List<String> formattedLocationNameList = new ArrayList<String>();
		for (Location location : locationList) {
			String s = location.getCityName() + ", " + location.getStateName() + ", " + location.getCountryName();
			formattedLocationNameList.add (s);
		}
		return formattedLocationNameList;
	}
	
	/**
	 * Gets the current timestamp (YYYMMddHHmmssSS) as a string.
	 * 
	 * @return the current timestamp.
	 */
	public static String getCurrentTimestamp () {
		String result = null;
		String pattern = "YYYMMddHHmmssSS";
	    SimpleDateFormat sdf = new SimpleDateFormat (pattern);
	    result = sdf.format (new Date());
	    return result;
	}	
	
}
