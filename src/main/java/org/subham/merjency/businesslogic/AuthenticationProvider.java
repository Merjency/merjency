package org.subham.merjency.businesslogic;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import org.subham.merjency.database.UserDetailsRepository;
import org.subham.merjency.model.resources.UserDetails;

/**
 * @author Subham Santra
 * 
 * @implSpec
 *           <li>Authenticates users based on given maild and pin</li>
 *           <li>Return {true, user-details} on successful authentication</li>
 *           <li>Return {false, error-message} on un-successful authentication
 *           </li>
 * 
 */
@Component
public class AuthenticationProvider {

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	public Pair<Boolean, ?> authenticate(String mailId, String pin) {
		Optional<UserDetails> foundByEmailId = userDetailsRepository.findByEmailId(mailId);

		if (foundByEmailId.isEmpty()) {
			return Pair.of(true, "EMAIL NOT FOUND");
		}

		UserDetails userDetails = foundByEmailId.get();

		if (userDetails.getPin().equals(pin)) {
			return Pair.of(Boolean.TRUE, userDetails);
		}

		return Pair.of(Boolean.FALSE, "USER PIN MISMATCH");
	}
}
