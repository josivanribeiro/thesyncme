package com.thesyncme.presentation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.thesyncme.business.entities.Comment;
import com.thesyncme.business.entities.Place;
import com.thesyncme.business.entities.User;

/**
 * User Friends Managed Bean.
 * 
 * @author Josivan Ribeiro
 *
 */
@Component
@Scope(value="request")
public class UserFriendsBean extends AbstractBaseBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger (UserFriendsBean.class);
	
	private List<Integer> pageNumberPhotoList = new ArrayList<Integer>();
	private List<User> friendList;
	private List<Place> recommendedPlaceList;
	private List<Comment> commentList;
	
	private User user1;
	private User user2;
	private User user3;
	private User user4;
	
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
	
	@PostConstruct
    public void init() {
		initPageNumberPhotoList  ();
		initUsers ();
		loadFriendsList ();
		initPlaces ();
		loadCommentList ();
		loadRecommendedPlaceList ();
    }
	
	private void initPageNumberPhotoList () {
		for (int i = 1; i < 13; i++) {
			pageNumberPhotoList.add (i);
		}		
	}

	public List<Integer> getPageNumberPhotoList() {
		return pageNumberPhotoList;
	}

	public void setPageNumberPhotoList(List<Integer> pageNumberPhotoList) {
		this.pageNumberPhotoList = pageNumberPhotoList;
	}
	
	public List<User> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<User> friendList) {
		this.friendList = friendList;
	}
	
	public List<Place> getRecommendedPlaceList() {
		return recommendedPlaceList;
	}

	public void setRecommendedPlaceList(List<Place> recommendedPlaceList) {
		this.recommendedPlaceList = recommendedPlaceList;
	}
	
	public List<Comment> getCommentList() {
		return commentList;
	}
	
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public void doSomething() {
		// code
	}
	
	private void initUsers () {
		
		Place place1 = new Place ();
		place1.setUsername("On The Fly Bar");
		
		Place place2 = new Place ();
		place2.setUsername("Pump Fitness");
		
		Place place3 = new Place ();
		place3.setUsername("Somewhere");
		
		Place place4 = new Place ();
		place4.setUsername("Apple Inc.");
		
		String imagesPath = "resources/images/";
		user1 = new User ();
		user1.setUsername ("Fabi Monteiro");
		profileImage1 = imagesPath + "friend_1_reduced_photo.jpg";
		user1.setProfileImagePath (profileImage1);
		user1.setReducedProfileImagePath("resources/images/user_1_reduced_profile_img.gif");		
		user1.setNumberRecommendations (10);
		user1.setNumberComments (3);
		user1.setNumberPhotos (5);
		user1.setCurrentPlace (place1);
		
		user2 = new User ();
		user2.setUsername ("Aline Souza");
		profileImage2 = imagesPath + "friend_2_reduced_photo.jpg";
		user2.setProfileImagePath (profileImage2);
		user2.setReducedProfileImagePath("resources/images/user_2_reduced_profile_img.gif");
		user2.setNumberRecommendations (10);
		user2.setNumberComments (3);
		user2.setNumberPhotos (5);
		user2.setCurrentPlace (place2);
		
		user3 = new User ();
		user3.setUsername ("Sibele Ferreira");
		profileImage3 = imagesPath + "friend_3_reduced_photo.jpg";
		user3.setProfileImagePath (profileImage3);
		user3.setReducedProfileImagePath("resources/images/user_3_reduced_profile_img.gif");
		user3.setNumberRecommendations (10);
		user3.setNumberComments (3);
		user3.setNumberPhotos (5);
		user3.setCurrentPlace (place3);
		
		user4 = new User ();
		user4.setUsername ("Steve Jobs");
		profileImage4 = imagesPath + "friend_4_reduced_photo.jpg";
		user4.setProfileImagePath (profileImage4);
		user4.setReducedProfileImagePath("resources/images/place_2_reduced_profile_img.gif");
		user4.setNumberRecommendations (10);
		user4.setNumberComments (3);
		user4.setNumberPhotos (5);
		user4.setCurrentPlace (place4);
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
	
	private void loadFriendsList () {
		friendList = new ArrayList<User>();
		friendList.add (user1);
		friendList.add (user2);
		friendList.add (user3);
		friendList.add (user4);
	}
	
	private void loadRecommendedPlaceList () {
		recommendedPlaceList = new ArrayList<Place>();
		recommendedPlaceList.add (place1);
		recommendedPlaceList.add (place2);
		recommendedPlaceList.add (place3);
		recommendedPlaceList.add (place4);
		recommendedPlaceList.add (place5);
		recommendedPlaceList.add (place6);
		recommendedPlaceList.add (place7);
		recommendedPlaceList.add (place8);
		recommendedPlaceList.add (place9);
		recommendedPlaceList.add (place10);
		recommendedPlaceList.add (place11);
		recommendedPlaceList.add (place12);
	}
	
	private void loadCommentList () {
		Comment comment1 = new Comment ();
		comment1.setCommentId (new Long(1));
		comment1.setFromUser (user1);
		comment1.setToPlace (place1);
		comment1.setComment ("Excelente acadêmia. Oferece uma ótima infraestrutura!");
		comment1.setCreationDate (new Date());
		
		Comment comment2 = new Comment ();
		comment2.setCommentId (new Long (2));
		comment2.setFromUser (user2);
		comment2.setToPlace (place2);
		comment2.setComment ("Bom atendimento e gente bonita. Recomendo!");
		comment2.setCreationDate (new Date());
		
		Comment comment3 = new Comment ();
		comment3.setCommentId (new Long (3));
		comment3.setFromUser (user3);
		comment3.setToPlace (place4);
		comment3.setComment ("Produtos de excelente qualidade!");
		comment3.setCreationDate (new Date());
		
		Comment comment4 = new Comment ();
		comment4.setCommentId (new Long (4));
		comment4.setFromUser (user4);
		comment4.setToPlace (place4);
		comment4.setComment ("Empresa visionária!");
		comment4.setCreationDate (new Date());
		
		commentList = new ArrayList<Comment>();
		commentList.add (comment1);
		commentList.add (comment2);
		commentList.add (comment3);
		commentList.add (comment4);
		
	}
	
}
