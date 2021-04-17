package org.subham.merjency.database;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.subham.merjency.model.resources.HospitalDetails;

@Repository
public interface HospitalDetailsRepository extends CrudRepository<HospitalDetails, Long> {
	Optional<HospitalDetails> findById(Long id);
	List<HospitalDetails> findAll();
	List<HospitalDetails> findByZipCode(String zipCode);
}
