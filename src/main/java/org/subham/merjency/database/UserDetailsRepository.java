package org.subham.merjency.database;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.subham.merjency.model.resources.UserDetails;

@Repository
public interface UserDetailsRepository extends CrudRepository<UserDetails, String>{
	Optional<UserDetails> findByUserName(String userName);
}
