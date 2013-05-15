package com.thesyncme.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.thesyncme.business.entities.Activity;
import com.thesyncme.business.entities.ActivityType;
import com.thesyncme.business.entities.Advertisement;
import com.thesyncme.business.entities.Company;
import com.thesyncme.business.entities.Education;
import com.thesyncme.business.entities.Language;
import com.thesyncme.business.entities.Location;
import com.thesyncme.business.entities.Photo;
import com.thesyncme.business.entities.Place;
import com.thesyncme.business.entities.Position;
import com.thesyncme.business.entities.User;
import com.thesyncme.business.services.CompanyService;
import com.thesyncme.business.services.EducationService;
import com.thesyncme.business.services.LanguageService;
import com.thesyncme.business.services.LocationService;
import com.thesyncme.business.services.PositionService;
import com.thesyncme.exceptions.BusinessException;
import com.thesyncme.helper.Helper;

/**
 * User Places Managed Bean.
 * 
 * @author Josivan Ribeiro
 *
 */
@Component
public class UserPlacesBean extends AbstractBaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger (UserPlacesBean.class);
	
	private List<Place> topPlaceList;
	private List<Place> placeList;
	private List<Place> placeYouMayKnowList;
	private List<Place> placeResultList;
	private User user;
		
	private Place place1;
	private Place place2;
	private Place place3;
	private Place place4;
	private Place place5;
	private Place place6;
	private Place place7;
	private Place place8;
	private Place place9;
	private Place place10;
	private Place place11;
	private Place place12;
	
	private String profileImage1;
	private String profileImage2;
	private String profileImage3;
	private String profileImage4;
	private String profileImage5;
	private String profileImage6;
	private String profileImage7;
	private String profileImage8;
	private String profileImage9;
	private String profileImage10;
	private String profileImage11;
	private String profileImage12;
	
	private String findPlacesCity;
	private String findPlacesDistrictOrStreet;
	private String findPlacesPlaceName;
	
	private LocationService locationService;
	private EducationService educationService;
	private CompanyService companyService;
	private PositionService positionService;
	private LanguageService languageService;
	
	@PostConstruct
    public void init() {
		initPlaces ();
		loadTopPlaceList ();
		loadPlaceList ();
		loadPlaceYouMayKnowList ();
		loadPlaceResultList ();
		loadUser ();
		loadUserActivities ();
    }
	
	public List<Place> getTopPlaceList () {
		return topPlaceList;
	}

	public void setTopPlaceList (List<Place> topPlaceList) {
		this.topPlaceList = topPlaceList;
	}
	
	public List<Place> getPlaceList() {
		return placeList;
	}

	public void setPlaceList(List<Place> placeList) {
		this.placeList = placeList;
	}
	
	public List<Place> getPlaceYouMayKnowList() {
		return placeYouMayKnowList;
	}

	public void setPlaceYouMayKnowList(List<Place> placeYouMayKnowList) {
		this.placeYouMayKnowList = placeYouMayKnowList;
	}
	
	public List<Place> getPlaceResultList() {
		return placeResultList;
	}

	public void setPlaceResultList(List<Place> placeResultList) {
		this.placeResultList = placeResultList;
	}

	public String getFindPlacesCity() {
		return findPlacesCity;
	}

	public void setFindPlacesCity(String findPlacesCity) {
		this.findPlacesCity = findPlacesCity;
	}

	public String getFindPlacesDistrictOrStreet() {
		return findPlacesDistrictOrStreet;
	}

	public void setFindPlacesDistrictOrStreet(String findPlacesDistrictOrStreet) {
		this.findPlacesDistrictOrStreet = findPlacesDistrictOrStreet;
	}

	public String getFindPlacesPlaceName() {
		return findPlacesPlaceName;
	}

	public void setFindPlacesPlaceName(String findPlacesPlaceName) {
		this.findPlacesPlaceName = findPlacesPlaceName;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public void setLocationService(LocationService locationService) {
		this.locationService = locationService;
	}

	public void setEducationService(EducationService educationService) {
		this.educationService = educationService;
	}

	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}

	public void setLanguageService(LanguageService languageService) {
		this.languageService = languageService;
	}
	
	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}

	/**
	 * Auto complete operation for the city field.
	 * 
	 * @param prefix the prefix used during the search.
	 * @return a list of string representing the search result.
	 */
	public List<String> autoCompleteCityName (String prefix) {
		logger.info ("Start executing the method autoCompleteCityName.");
		logger.info ("prefix [" + prefix + "]");
		List<String> result = new ArrayList<String>();
		List<Location> locationList = new ArrayList<Location>();
		try {
			if (prefix != null && !"".equals (prefix.trim())) {
				locationList = locationService.findByCityNamePrefix (prefix);
	        } else {
	        	locationList = locationService.findAll ();
	        }
			if (!locationList.isEmpty()) {
				result = Helper.formatLocationName (locationList);
			}
		} catch (BusinessException e) {
			String errorMessage = "An error occurred while finding the city name by prefix.";
			logger.error (errorMessage, e);
			addErrorMessage (errorMessage);
		}
        logger.info ("Finish executing the method autoCompleteCityName.");
        return result;
    }
	
	/**
	 * Auto complete operation for the education field.
	 * 
	 * @param prefix the prefix used during the search.
	 * @return a list of string representing the search result.
	 */
	public List<String> autoCompleteInstitutionName (String prefix) {
		logger.info ("Start executing the method autoCompleteInstitutionName.");
		logger.info ("prefix [" + prefix + "]");
		List<String> result = new ArrayList<String>();
		List<Education> educationList = new ArrayList<Education>();
		try {
			if (prefix != null && !"".equals (prefix.trim())) {
				educationList = educationService.findByInstitutionNamePrefix (prefix);
	        } else {
	        	educationList = educationService.findAll ();
	        }
			if (!educationList.isEmpty()) {
				for (Education education : educationList) {
					String institutionName = education.getInstitutionName();
					result.add (institutionName);
				}
			}
		} catch (BusinessException e) {
			String errorMessage = "An error occurred while finding the institution name by prefix.";
			logger.error (errorMessage, e);
			addErrorMessage (errorMessage);
		}
        logger.info ("Finish executing the method autoCompleteInstitutionName.");
        return result;
    }
	
	/**
	 * Auto complete operation for the company field.
	 * 
	 * @param prefix the prefix used during the search.
	 * @return a list of string representing the search result.
	 */
	public List<String> autoCompleteCompanyName (String prefix) {
		logger.info ("Start executing the method autoCompleteCompanyName.");
		logger.info ("prefix [" + prefix + "]");
		List<String> result = new ArrayList<String>();
		List<Company> companyList = new ArrayList<Company>();
		try {
			if (prefix != null && !"".equals (prefix.trim())) {
				companyList = companyService.findByCompanyNamePrefix (prefix);
	        } else {
	        	companyList = companyService.findAll ();
	        }
			if (!companyList.isEmpty()) {
				for (Company company : companyList) {
					String companyName = company.getName();
					result.add (companyName);
				}
			}
		} catch (BusinessException e) {
			String errorMessage = "An error occurred while finding the company name by prefix.";
			logger.error (errorMessage, e);
			addErrorMessage (errorMessage);
		}
        logger.info ("Finish executing the method autoCompleteCompanyName.");
        return result;
    }
	
	/**
	 * Auto complete operation for the position field.
	 * 
	 * @param prefix the prefix used during the search.
	 * @return a list of string representing the search result.
	 */
	public List<String> autoCompletePositionName (String prefix) {
		logger.info ("Start executing the method autoCompletePositionName.");
		logger.info ("prefix [" + prefix + "]");
		List<String> result = new ArrayList<String>();
		List<Position> positionList = new ArrayList<Position>();
		try {
			if (prefix != null && !"".equals (prefix.trim())) {
				positionList = positionService.findByPositionNamePrefix (prefix);
	        } else {
	        	positionList = positionService.findAll ();
	        }
			if (!positionList.isEmpty()) {
				for (Position position : positionList) {
					String positionName = position.getName();
					result.add (positionName);
				}
			}
		} catch (BusinessException e) {
			String errorMessage = "An error occurred while finding the position name by prefix.";
			logger.error (errorMessage, e);
			addErrorMessage (errorMessage);
		}
        logger.info ("Finish executing the method autoCompletePositionName.");
        return result;
    }
	
	/**
	 * Auto complete operation for the languages field.
	 * 
	 * @param prefix the prefix used during the search.
	 * @return a list of string representing the search result.
	 */
	public List<String> autoCompleteLanguageName (String prefix) {
		logger.info ("Start executing the method autoCompleteLanguageName.");
		logger.info ("prefix [" + prefix + "]");
		List<String> result = new ArrayList<String>();
		List<Language> languageList = new ArrayList<Language>();
		try {
			if (prefix != null && !"".equals (prefix.trim())) {
				languageList = languageService.findByLanguageNamePrefix (prefix);
	        } else {
	        	languageList = languageService.findAll ();
	        }
			if (!languageList.isEmpty()) {
				for (Language language : languageList) {
					String languageName = language.getName();
					result.add (languageName);
				}
			}
		} catch (BusinessException e) {
			String errorMessage = "An error occurred while finding the language name by prefix.";
			logger.error (errorMessage, e);
			addErrorMessage (errorMessage);
		}
        logger.info ("Finish executing the method autoCompleteLanguageName.");
        return result;
    }
	
	private void initPlaces () {
		String imagesPath = "resources/images/";
		place1 = new Place ();
		place1.setUsername ("MobiDick Fitness");
		profileImage1 = imagesPath + "place_1_profile_img.gif";
		place1.setProfileImagePath (profileImage1);
		place1.setNumberRecommendations (10);
		place1.setNumberComments (3);
		place1.setNumberPhotos (5);
		place2 = new Place ();
		profileImage2 = imagesPath + "place_2_profile_img.gif";
		place2.setUsername ("The Office Bar");
		place2.setProfileImagePath (profileImage2);
		place2.setNumberRecommendations (9);
		place2.setNumberComments (2);
		place2.setNumberPhotos (3);
		place3 = new Place ();
		profileImage3 = imagesPath + "place_3_profile_img.gif";
		place3.setUsername ("Bossa Nova Bar");
		place3.setProfileImagePath (profileImage3);
		place3.setNumberRecommendations (8);
		place3.setNumberComments (3);
		place3.setNumberPhotos (5);
		place4 = new Place ();
		profileImage4 = imagesPath + "place_4_profile_img.gif";
		place4.setUsername ("Bar Baroneza");
		place4.setProfileImagePath (profileImage4);
		place4.setNumberRecommendations (7);
		place4.setNumberComments (2);
		place4.setNumberPhotos (1);
		
		place5 = new Place();
		profileImage5 = imagesPath + "place_5_profile_img.gif"; 
		place5.setUsername("Mexican Bar");
		place5.setProfileImagePath (profileImage5);
		place5.setNumberRecommendations (4);
		place5.setNumberComments (1);
		place5.setNumberPhotos (2);
		
		place6 = new Place ();
		profileImage6 = imagesPath + "place_6_profile_img.gif";
		place6.setUsername("Comenda Bar & Grill");
		place6.setProfileImagePath (profileImage6);
		place6.setNumberRecommendations (2);
		place6.setNumberComments (7);
		place6.setNumberPhotos (10);
		
		place7 = new Place ();
		profileImage7 = imagesPath + "place_7_profile_img.gif";
		place7.setUsername("VOX Bar");
		place7.setProfileImagePath (profileImage7);
		place7.setNumberRecommendations (8);
		place7.setNumberComments (2);
		place7.setNumberPhotos (2);
		
		place8 = new Place ();
		profileImage8 = imagesPath + "place_8_profile_img.gif";
		place8.setUsername("Layout 80");
		place8.setProfileImagePath (profileImage8);
		place8.setNumberRecommendations (2);
		place8.setNumberComments (1);
		place8.setNumberPhotos (5);
		
		place9 = new Place ();
		profileImage9 = imagesPath + "place_9_profile_img.gif";
		place9.setUsername("Aoca Bar");
		place9.setProfileImagePath (profileImage9);
		place9.setNumberRecommendations (2);
		place9.setNumberComments (1);
		place9.setNumberPhotos (5);
		
		place10 = new Place ();
		profileImage10 = imagesPath + "place_10_profile_img.gif";
		place10.setUsername("Bagdad Café");
		place10.setProfileImagePath (profileImage10);
		place10.setNumberRecommendations (7);
		place10.setNumberComments (5);
		place10.setNumberPhotos (11);
		
		place11 = new Place ();
		profileImage11 = imagesPath + "place_11_profile_img.gif";
		place11.setUsername("Akai Sushi Bar");
		place11.setProfileImagePath (profileImage11);
		place11.setNumberRecommendations (2);
		place11.setNumberComments (1);
		place11.setNumberPhotos (6);
		
		place12 = new Place ();
		profileImage12 = imagesPath + "place_12_profile_img.gif";
		place12.setUsername("Swimex Academia");
		place12.setProfileImagePath (profileImage12);
		place12.setNumberRecommendations (5);
		place12.setNumberComments (5);
		place12.setNumberPhotos (5);		
	}
	
	private void loadTopPlaceList () {
		topPlaceList = new ArrayList<Place>();
		topPlaceList.add (place1);
		topPlaceList.add (place2);
		topPlaceList.add (place3);
		topPlaceList.add (place4);
	}
	
	private void loadPlaceList () {
		placeList = new ArrayList<Place>();
		placeList.add (place1);
		placeList.add (place2);
		placeList.add (place3);
		placeList.add (place4);
	}
	
	private void loadPlaceYouMayKnowList () {
		placeYouMayKnowList = new ArrayList<Place>();
		placeYouMayKnowList.add (place1);
		placeYouMayKnowList.add (place2);
		placeYouMayKnowList.add (place3);
		placeYouMayKnowList.add (place4);
		placeYouMayKnowList.add (place5);
		placeYouMayKnowList.add (place6);
		placeYouMayKnowList.add (place7);
		placeYouMayKnowList.add (place8);
		placeYouMayKnowList.add (place9);
		placeYouMayKnowList.add (place10);
		placeYouMayKnowList.add (place11);
		placeYouMayKnowList.add (place12);
	}
	
	private void loadPlaceResultList () {
		placeResultList = new ArrayList<Place>();
		placeResultList.add (place1);
		placeResultList.add (place2);
		placeResultList.add (place3);
		placeResultList.add (place4);		
	}
	
	private void loadUser () {
		
		// creating a new user
		
		user = new User ();
		user.setUsername ("Josivan Ribeiro");
		
		// creating the user company
		
		Company company = new Company();
		company.setCompanyId(new Long(1));
		company.setName("TheSyncMe");
		
		user.setCompany (company);
		
		// setting the user education list
		
		Education education1 = new Education ();
		education1.setEducationId (new Long(1));
		education1.setInstitutionName ("PUCPR, ");
		
		Education education2 = new Education();
		education2.setEducationId (new Long(2));
		education2.setInstitutionName ("COC");
		
		List<Education> educationList = new ArrayList<Education>();
		educationList.add (education1);
		educationList.add (education2);
		
		user.setEducationList (educationList);
		
		// creating the user location
		
		// setting the user location
		Location location = new Location();
		location.setLocationId (new Long(1));
		location.setCityName ("Curitiba");
		location.setStateName ("Paraná");
		location.setCountryName ("Brasil");
		
		user.setLocation (location);
		
		// creating the user languages
		
		Language language1 = new Language();
		language1.setLanguageId(1);
		language1.setName("English, ");
		
		Language language2 = new Language ();
		language2.setLanguageId(2);
		language2.setName("Portuguese");
		
		List<Language> languageList = new ArrayList<Language>();
		languageList.add(language1);
		languageList.add(language2);
		
		user.setLanguageList(languageList);
		
		// creating the user location
		Place place = new Place();
		place.setUsername ("On The Fly Bar");
		
		user.setCurrentPlace(place);
		
		// creating the user recommendations, comments and photos
		
		user.setNumberRecommendations(10);
		user.setNumberComments(2);
		user.setNumberPhotos(5);
		
		// creating the user notifications, friend requests and updates
		
		user.setNumberNotifications(2);
		user.setNumberFriendRequests(3);
		user.setNumberUpdates(9);		
	}
	
	private void loadUserActivities () {
		
		// creating the users and places
		
		User user1 = new User ();
		user1.setUsername("Josivan Silva");
		
		User user2 = new User ();
		user2.setUsername("Aline Souza");
		
		Place place1 = new Place ();
		place1.setUsername("MobiDick Fitness");
		
		Place place2 = new Place ();
		place2.setUsername("Bossa Nova Bar");
		place2.setReducedProfileImagePath("resources/images/place_2_reduced_profile_img.gif");
		
		Place place3 = new Place ();
		place3.setUsername ("Bar Baroneza");
		
		Place place4 = new Place ();
		place4.setUsername ("Mexican Bar");
		
		Place place5 = new Place ();
		place5.setUsername ("VOX Bar");
		
		Place place6 = new Place ();
		place6.setUsername ("Aoca Bar");
		
		Place place7 = new Place ();
		place7.setUsername ("Bagdad Café");

		// creating the activity types
		ActivityType activityType1 = new ActivityType ();
		activityType1.setActivityTypeId("1");
		activityType1.setDescription("became fan of");
		
		ActivityType activityType2 = new ActivityType ();
		activityType2.setActivityTypeId("2");
		activityType2.setDescription("updated its About information");
		
		ActivityType activityType3 = new ActivityType ();
		activityType3.setActivityTypeId("3");
		activityType3.setDescription("recommended");
		
		ActivityType activityType4 = new ActivityType ();
		activityType4.setActivityTypeId("4");
		activityType4.setDescription("commented about");
		
		ActivityType activityType5 = new ActivityType ();
		activityType5.setActivityTypeId("5");
		activityType5.setDescription("advertisement");
		
		ActivityType activityType6 = new ActivityType ();
		activityType6.setActivityTypeId("6");
		activityType6.setDescription("photo");
		
		// creating the activities
		
		Activity activity1 = new Activity ();
		activity1.setFromUser (user1);
		activity1.setToUser (place1);
		activity1.setActivityType (activityType1);
		activity1.setCreationDate (new Date());
		
		Activity activity2 = new Activity ();
		activity2.setFromUser (place1);
		activity2.setActivityType (activityType2);
		activity2.setCreationDate (new Date());
		
		Activity activity3 = new Activity ();
		activity3.setFromUser (user1);
		activity3.setToUser (place2);
		activity3.setActivityType (activityType3);
		activity3.setCreationDate (new Date());
		
		Activity activity4 = new Activity ();
		activity4.setFromUser (user2);
		activity4.setToUser (place2);
		activity4.setActivityType (activityType4);
		activity4.setCreationDate (new Date());
		
		Activity activity5 = new Activity ();
		activity5.setFromUser (user1);
		activity5.setToUser (place1);
		activity5.setActivityType (activityType3);
		activity5.setCreationDate (new Date());
		
		Activity activity6 = new Activity ();
		activity6.setFromUser (user2);
		activity6.setToUser (place1);
		activity6.setActivityType (activityType3);
		activity6.setCreationDate (new Date());
		
		Activity activity7 = new Activity ();
		activity7.setFromUser (user2);
		activity7.setToUser (place3);
		activity7.setActivityType (activityType3);
		activity7.setCreationDate (new Date());
		
		Activity activity8 = new Activity ();
		activity8.setFromUser (user2);
		activity8.setToUser (place4);
		activity8.setActivityType (activityType3);
		activity8.setCreationDate (new Date());
		
		Activity activity9 = new Activity ();
		activity9.setFromUser (user2);
		activity9.setToUser (place5);
		activity9.setActivityType (activityType3);
		activity9.setCreationDate (new Date());
		
		Activity activity10 = new Activity ();
		activity10.setFromUser (user1);
		activity10.setToUser (place3);
		activity10.setActivityType (activityType4);
		activity10.setCreationDate (new Date());
		
		Activity activity11 = new Activity ();
		activity11.setFromUser (user1);
		activity11.setToUser (place4);
		activity11.setActivityType (activityType4);
		activity11.setCreationDate (new Date());
		
		Activity activity12 = new Activity ();
		activity12.setFromUser (user1);
		activity12.setToUser (place5);
		activity12.setActivityType (activityType4);
		activity12.setCreationDate (new Date());
		
		Activity activity13 = new Activity ();
		activity13.setFromUser (place2);
		activity13.setActivityType (activityType2);
		activity13.setCreationDate (new Date());
		
		Activity activity14 = new Activity ();
		activity14.setFromUser (place3);
		activity14.setActivityType (activityType2);
		activity14.setCreationDate (new Date());
		
		Activity activity15 = new Activity ();
		activity15.setFromUser (place4);
		activity15.setActivityType (activityType2);
		activity15.setCreationDate (new Date());
		
		Activity activity16 = new Activity ();
		activity16.setFromUser (place4);
		activity16.setActivityType (activityType2);
		activity16.setCreationDate (new Date());
		
		Activity activity17 = new Activity ();
		activity17.setFromUser (place2);
		activity17.setActivityType (activityType5);
		activity17.setCreationDate (new Date());
		
		Activity activity18 = new Activity ();
		activity18.setFromUser (place2);
		activity18.setActivityType (activityType6);
		activity18.setCreationDate (new Date());
		
		// creating the advertisement
		
		Advertisement advertisement1 = new Advertisement ();
		advertisement1.setAdvertisementId ("1");
		advertisement1.setMessage ("Terça a partir das 22h tem a festa, \"Especial Fim de Ano\", Festa especial de encerramento de temporada.");
		
		
		// creating the list of user who enjoyed
		
		List<User> userWhoEnjoyedList = new ArrayList<User>();
		userWhoEnjoyedList.add(user1);
		userWhoEnjoyedList.add(user2);
		
		activity17.setUserWhoEnjoyedList(userWhoEnjoyedList);
		
		activity17.setAdvertisement(advertisement1);
		
		// creating the list of photos
		
		String imagesPath = "resources/images/";
		
		Photo photo1 = new Photo ();
		photo1.setPhotoId("1");
		photo1.setName("photo1");
		photo1.setFilePath(imagesPath + "place_1_reduced_photo.jpg");
		
		Photo photo2 = new Photo ();
		photo2.setPhotoId("2");
		photo2.setName("photo2");
		photo2.setFilePath(imagesPath + "place_2_reduced_photo.jpg");
		
		Photo photo3 = new Photo ();
		photo3.setPhotoId("3");
		photo3.setName("photo3");
		photo3.setFilePath(imagesPath + "place_3_reduced_photo.jpg");
		
		List<Photo> photoList = new ArrayList<Photo>();
		photoList.add(photo1);
		photoList.add(photo2);
		photoList.add(photo3);
		
		activity18.setPhotoList (photoList);
		activity18.setUserWhoEnjoyedList (userWhoEnjoyedList);
				
		List<Activity> activityList = new ArrayList<Activity>();
		activityList.add (activity1);
		activityList.add (activity2);
		activityList.add (activity3);
		activityList.add (activity4);
		activityList.add (activity5);
		activityList.add (activity6);
		activityList.add (activity7);
		activityList.add (activity9);
		activityList.add (activity10);
		activityList.add (activity11);
		activityList.add (activity12);
		activityList.add (activity13);
		activityList.add (activity14);
		activityList.add (activity15);
		activityList.add (activity16);
		activityList.add (activity17);
		activityList.add (activity18);
				
		user.setActivityList (activityList);		
	}
	
	public void doSomething() {
		// code
	}
	
}
