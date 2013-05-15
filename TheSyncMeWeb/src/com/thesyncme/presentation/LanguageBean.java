package com.thesyncme.presentation;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.thesyncme.constants.Constants;

/**
 * Language Managed Bean.
 * 
 * @author Josivan Ribeiro
 *
 */
@Component
@Scope(value="request")
public class LanguageBean extends AbstractBaseBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger logger = Logger.getLogger (LanguageBean.class);
	private String localeCode = getDefaultLanguageCode();
	private Map<String,Object> languageMap;
	
	@PostConstruct
    public void init() {
		loadLanguageMap();
	}
	
	public Map<String, Object> getLanguageMap() {
		return languageMap;
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode (String localeCode) {
		this.localeCode = localeCode;
	}
	
	/**
	 * Loads the language map.
	 */
	private void loadLanguageMap () {
		languageMap = new LinkedHashMap<String,Object>();
		languageMap.put (Constants.EN_US_LANGUAGE, Locale.ENGLISH);
		languageMap.put (Constants.PT_BR_LANGUAGE, Constants.BRAZIL);		
	}
	
	/**
	 * Updates the default Locale according with the selected language.
	 * 
	 * @param event ValueChangeEvent object.
	 */
	public void languageValueChanged (ValueChangeEvent e) {
		logger.info ("Start executing the method languageValueChanged.");
		if (e.getNewValue() != null) {
			String eventValue = (String) e.getNewValue ();
			if (eventValue != null && !"".equals (eventValue.trim())) {
				this.localeCode = eventValue;
				logger.info("localeCode " + localeCode);
			}
		}
		logger.info ("Finish executing the method languageValueChanged.");
    }
	
}
