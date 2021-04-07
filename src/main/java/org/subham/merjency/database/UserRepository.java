package org.subham.merjency.database;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.subham.merjency.model.resources.UserDetails;

@Component
public class UserRepository {
	Map<String, UserDetails> userDetails = new HashMap<>();
	
	public void add(UserDetails details) {
		userDetails.put(details.getUserName(), details);
	}
	
	public Optional<UserDetails> findByUserName(String userName) {
		return Optional.of(userDetails.get(userName));
	}
	
}
