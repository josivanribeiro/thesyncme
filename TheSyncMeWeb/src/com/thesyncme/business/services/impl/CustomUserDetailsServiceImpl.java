package com.thesyncme.business.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.thesyncme.business.entities.Place;
import com.thesyncme.business.entities.Role;
import com.thesyncme.business.entities.User;
import com.thesyncme.dao.impl.PlaceDAOImpl;
import com.thesyncme.dao.impl.UserDAOImpl;
import com.thesyncme.enums.Roles;
import com.thesyncme.exceptions.DataAccessException;

@Service ("customUserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {
	/**
	 * Default logger.
	 */
	private static Logger logger = Logger.getLogger (CustomUserDetailsServiceImpl.class);
	/**
	 * Attribute that defines the User DAO instance.
	 */
	private UserDAOImpl userDAO;
	/**
	 * Attribute that defines the Place DAO instance. 
	 */
	private PlaceDAOImpl placeDAO;
		
	public void setUserDAO (UserDAOImpl userDAO) {
		this.userDAO = userDAO;
	}
	
	public void setPlaceDAO (PlaceDAOImpl placeDAO) {
		this.placeDAO = placeDAO;
	}
	
	@Override
	public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException {
		logger.info ("Start executing the method findByUsername.");
		org.springframework.security.core.userdetails.User userDetails = loadUserByEmail (username);
		if (userDetails == null) {
			throw new UsernameNotFoundException("User account for name \"" + username + "\" not found.");
		}
		logger.info ("Finishing executing the method findByUsername.");
		return userDetails;
	}
	
	/**
	 * Loads the user given its email.
	 * 
	 * @param email the email.
	 * @return an User instance.
	 * @throws UsernameNotFoundException
	 */
	private org.springframework.security.core.userdetails.User loadUserByEmail (String email) throws UsernameNotFoundException {
		logger.info ("Start executing the method loadUserByEmail.");
		org.springframework.security.core.userdetails.User userDetails = null;
		User user = null;
		Place place = null;
		String userEmail = null;
		String userPassword = null;
		List<Role> userRoleList = null;
		boolean isEnabled = true;
		boolean isAccountNonExpired = true;
		boolean isCredentialsNonExpired = true;
		boolean isAccountNonLocked = true;
		try {
			user = new User ();
			user.setEmail (email);
			user = userDAO.findByEmail (user);
			if (user != null
					&& user.getEmail() != null
					&& !"".equals (user.getEmail())
					&& user.getPassword() != null
					&& !"".equals (user.getPassword ())) {
				userEmail = user.getEmail ();
				userPassword = user.getPassword ();
				userRoleList = user.getRoleList ();
			} else {
				place = new Place ();
				place.setEmail (email);
				place = placeDAO.findByEmail (place);
				if (place != null
						&& place.getEmail() != null
						&& !"".equals(place.getEmail())
						&& place.getPassword() != null
						&& !"".equals (place.getPassword())) {
					userEmail = place.getEmail ();
					userPassword = place.getPassword ();
					userRoleList = place.getRoleList ();
				}
			}		
			if (userEmail != null
					&& !"".equals (userEmail)
					&& userPassword != null
					&& !"".equals (userPassword)) {
				boolean isAdmin = containsRoleAdmin (userRoleList);
				logger.info ("isAdmin [" + isAdmin + "]");
				userDetails = new org.springframework.security.core.userdetails.User (userEmail, userPassword, isEnabled, isAccountNonExpired, isCredentialsNonExpired, isAccountNonLocked, getAuthorities (isAdmin));
			}
		} catch (DataAccessException e) {
			String errorMessage = "User not found [" + email + "]";
			logger.error (errorMessage, e);
			throw new UsernameNotFoundException (errorMessage, e.getCause());
		}
		logger.info ("Finishing executing the method loadUserByEmail.");
		return userDetails;
	}
	
	/**
	 * Gets the granted authorities.
	 * 
	 * @param isAdmin flag that indicates if the the admin role must be included.
	 * @return a list of granted authority.
	 */
	private List<GrantedAuthority> getAuthorities (boolean isAdmin) {
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add (new GrantedAuthorityImpl (Roles.ROLE_USER.getRoleName ()));
        if (isAdmin) {
            authList.add (new GrantedAuthorityImpl (Roles.ROLE_ADMIN.getRoleName ()));
        }
        return authList;
    }
	
	/**
	 * Checks if a list of roles contains the admin role (ROLE_ADMIN).
	 * 
	 * @param roleList the role list.
	 * @return a boolean indicating if the list contains or not the role admin.
	 */
	private boolean containsRoleAdmin (List<Role> roleList) {
		boolean contains = false;
		for (Role role : roleList) {
			if (role.getRoleId().equals (Roles.ROLE_ADMIN.getRoleId().toString())) {
				contains = true;
				break;
			}
		}
		return contains;
	}

}
