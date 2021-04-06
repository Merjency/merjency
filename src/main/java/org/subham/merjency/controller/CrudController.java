package org.subham.merjency.controller;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.subham.merjency.database.HospitalRepository;
import org.subham.merjency.database.UserRepository;
import org.subham.merjency.model.DistanceUnit;
import org.subham.merjency.model.DistanceUtil;
import org.subham.merjency.model.GeoLocation;
import org.subham.merjency.model.HospitalDetails;
import org.subham.merjency.model.UserDetails;

@RestController
@RequestMapping("/api")
public class CrudController {
	@Autowired
	private UserRepository userDb; // Map <userName, details>

	@Autowired
	private HospitalRepository hospitalDb; // simple array-list

	@GetMapping("/")
	public String test() {
		return "Hello";
	}

	@PostMapping("/register/user")
	public ResponseEntity<?> registerUser(@RequestBody @Valid UserDetails details) {
		userDb.add(details);
		return ResponseEntity.ok("Done");
	}

	@PostMapping("/register/hospital")
	public ResponseEntity<?> registerHospital(@RequestBody @Valid HospitalDetails hospitalDetails) {
		hospitalDb.add(hospitalDetails);
		return ResponseEntity.ok("Done");
	}

	@GetMapping("/user/{userName}/get-hospital-list")
	public strictfp ResponseEntity<?> getHospitalListForUserName(@PathVariable String userName,
			@RequestBody @Valid GeoLocation userLocation) {

		ArrayList<HospitalDetails> list = hospitalDb.getDetails();
		TreeMap<Double, HospitalDetails> sortedMap = new TreeMap<>();

		for (var hospitalDetails : list) {
			Map<DistanceUnit, Double> matrix = DistanceUtil.getDistance(userLocation, hospitalDetails.getLocation());
			sortedMap.put(matrix.get(DistanceUnit.IN_KM), hospitalDetails);
		}

		return ResponseEntity.ok(sortedMap.values());
	}
}
