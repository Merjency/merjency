package org.subham.merjency.controller;

import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.subham.merjency.dataaccess.DataAccessMethodResponse;
import org.subham.merjency.dataaccess.HospitalDataAccess;
import org.subham.merjency.dataaccess.OrderingParam;
import org.subham.merjency.database.UserRepository;
import org.subham.merjency.model.GeoLocation;
import org.subham.merjency.model.resources.HospitalDetails;
import org.subham.merjency.model.resources.UserDetails;

@RestController
@RequestMapping("/api")
public class CrudController {
	@Autowired
	private HospitalDataAccess hospitalDataAccess;

	@Autowired
	private UserRepository userDb; // Map <userName, details>

	@GetMapping("/")
	public String test() {
		return "Hello";
	}

	@PostMapping("/register/user")
	public ResponseEntity<?> registerUser(@RequestBody UserDetails details) {
		userDb.add(details);
		return ResponseEntity.ok("Done");
	}

	@PostMapping("/register/hospital")
	public ResponseEntity<?> registerHospital(@RequestBody HospitalDetails hospitalDetails) {
		DataAccessMethodResponse accessMethodResponse = hospitalDataAccess.store(hospitalDetails);
		if (accessMethodResponse.equals(DataAccessMethodResponse.VALID_RESOURCE_INSERTION)) {
			return ResponseEntity.ok("Done");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}

	@GetMapping("/user/{userName}/get-hospital-list")
	public strictfp ResponseEntity<ArrayList<HospitalDetails>> getHospitalListForUserName(@PathVariable String userName,
			@RequestBody @Valid GeoLocation userLocation) {

		ArrayList<HospitalDetails> arrayList = hospitalDataAccess.getList(userLocation, OrderingParam.GPS_LOCATION);
		return ResponseEntity.ok(arrayList);
	}
}
