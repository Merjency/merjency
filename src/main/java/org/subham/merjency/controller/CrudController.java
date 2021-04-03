package org.subham.merjency.controller;

import java.util.ArrayList;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.subham.merjency.database.HospitalRepository;
import org.subham.merjency.database.UserRepository;
import org.subham.merjency.model.GeoLocation;
import org.subham.merjency.model.HospitalDetails;
import org.subham.merjency.model.UserDetails;

@RestController("api/")
public class CrudController {
	@Autowired
	private UserRepository userDb; // Map <userName, details>

	@Autowired
	private HospitalRepository hospitalDb; // simple array-list

	@PostMapping("register/user")
	public ResponseEntity<?> registerUser(@RequestBody UserDetails details) {
		userDb.add(details);
		return ResponseEntity.ok("Done");
	}

	@PostMapping("register/hospital")
	public ResponseEntity<?> registerHospital(@RequestBody HospitalDetails hospitalDetails) {
		hospitalDb.add(hospitalDetails);
		return ResponseEntity.ok("Done");
	}

	@GetMapping("user/{userName}/get-hospital-list")
	public strictfp ResponseEntity<?> getHospitalListForUserName(@PathVariable String userName,
			@RequestBody GeoLocation userLocation) {
		
		// Validation 
		// check if userName is in our database or not??? [y/n]
		
		ArrayList<HospitalDetails> list = hospitalDb.getDetails();
		TreeMap<Double, HospitalDetails> sortedMap = new TreeMap<>();
		
		// NOT-implemented
		// Prepare a min-heap of size K
		// by default k = 5;
		
		for (var hospitalDetails : list) {
			double calculatedDistance = hospitalDetails.getLocation().getDistance(userLocation);
			sortedMap.put(calculatedDistance, hospitalDetails);
		}
		
		return ResponseEntity.ok(sortedMap.values());
	}
}
