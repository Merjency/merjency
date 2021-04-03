package org.subham.merjency.database;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.subham.merjency.model.UserDetails;

@Component
public class UserRepository {
	Map<String, UserDetails> userDetails = new HashMap<>();
	
	public void add(UserDetails details) {
		userDetails.put(details.getUserName(), details);
	}
	
	public UserDetails getByUserName(String userName) {
		return userDetails.get(userName);
	}
	
}
