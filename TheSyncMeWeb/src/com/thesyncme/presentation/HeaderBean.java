package com.thesyncme.presentation;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.thesyncme.business.entities.User;

/**
 * Header Managed Bean.
 * 
 * @author Josivan Ribeiro
 *
 */
@Component
@Scope(value="request")
public class HeaderBean extends AbstractBaseBean {

	private String value;
    private List<String> autocompleteList;
    private List<User> userList;
    
    public String getValue() {
        return value;
    }

    public void setValue (String value) {
        this.value = value;
    }

    public List<String> getAutocompleteList() {
        return autocompleteList;
    }

    public void setAutocompleteList(List<String> autocompleteList) {
        this.autocompleteList = autocompleteList;
    }

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	@PostConstruct
    public void init() {
    	populateUserList ();
    	autocompleteList = new ArrayList<String>();
        for (User user : userList) {
            autocompleteList.add (user.getUsername());
        }
    }    
    
    private void populateUserList () {
    	userList = new ArrayList<User>();
    	for (int i = 0; i < 21; i++) {
    		User user = new User ();
    		String userName = "a" + i;
    		user.setUsername (userName);
    		userList.add (user);
    	}
    }

    public List<String> autoComplete (String prefix) {
        ArrayList<String> result = new ArrayList<String>();
        if ((prefix == null) || (prefix.length() == 0)) {
            for (int i = 0; i < 10; i++) {
                result.add (userList.get(i).getUsername());
            }
        } else {
            Iterator<User> iterator = userList.iterator();
            while (iterator.hasNext()) {
                User user = ((User) iterator.next());
                if ((user.getUsername() != null && user.getUsername().toLowerCase().indexOf (prefix.toLowerCase()) == 0)
                		|| "".equals(prefix)) {
                    result.add (user.getUsername());
                }
            }
        }
        return result;
    }    
	
}
