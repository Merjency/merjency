package org.subham.merjency.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.subham.merjency.businesslogic.HospitalDataAccess;
import org.subham.merjency.database.HospitalDetailsRepository;
import org.subham.merjency.database.UserDetailsRepository;
import org.subham.merjency.model.DummyResourceManager;
import org.subham.merjency.model.GeoLocation;
import org.subham.merjency.model.LocationFormatter;
import org.subham.merjency.model.resources.HospitalDetails;
import org.subham.merjency.model.resources.Resource;
import org.subham.merjency.model.resources.UserDetails;

/**
 * @author Subham Santra
 *
 */
/**
 * @author Admin
 *
 */
@RestController
@RequestMapping("/api")
public class CrudController {
	@Autowired
	private HospitalDetailsRepository hospitalDetailsRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	@Autowired
	private HospitalDataAccess hospitalDataAccess = new HospitalDataAccess();

	@GetMapping("/test")
	public String testApi() {
		return "WELCOME TO OUR MERJENCY SERVICES";
	}
	
	@GetMapping("/deploy")
	public String testDeploy() {
		return "HURRAY... CONTINUOUS INTEGRATION WORKING FINE WITH EVERY COMMIT \n \t\t !!!! DEPLOYED SUCCESSFULLY !!!!";
	}

	@GetMapping("/get-dummy-data")
	public ResponseEntity<?> getDummyData() {
		HospitalDetails hospitalDetails = DummyResourceManager.getDummyHospitalDetails();
		UserDetails userDetails = DummyResourceManager.getDummyUserDetails();
		Map<String, Resource> map = Map.of("userDetails", userDetails, "hospitalDetails", hospitalDetails);
		return ResponseEntity.ok(map);
	}

	@GetMapping("/hospitals")
	public List<HospitalDetails> getHospitals() {
		return hospitalDataAccess.findAll();
	}

	@PostMapping("/hospitals/registration")
	public HospitalDetails hospitalRegistration(@RequestBody HospitalDetails hospitalDetails) {
		HospitalDetails savedDetails = hospitalDataAccess.storeNewDetails(hospitalDetails);
		return savedDetails;
	}

	@PutMapping("/hospitals/{id}")
	public HospitalDetails updateHospitalDetails(@RequestBody HospitalDetails hospitalDetails) {
		return hospitalDataAccess.storeNewDetails(hospitalDetails);
	}

	@PostMapping("/users/registration")
	public UserDetails userRegistration(@RequestBody UserDetails userDetails) {
		UserDetails savedDetails = userDetailsRepository.save(userDetails);
		return savedDetails;
	}

	@PutMapping("/users/{userId}")
	public ResponseEntity<?> userUpdate(@PathVariable long userId, @RequestBody UserDetails userDetails) {
		Optional<UserDetails> findById = userDetailsRepository.findById(userId);
		if (findById.isEmpty())
			return ResponseEntity.notFound().build();

		UserDetails savedDetails = userDetailsRepository.save(userDetails);
		return ResponseEntity.ok(savedDetails);
	}

	@GetMapping("/users/{userId}")
	public Optional<UserDetails> getUserDetails(@PathVariable long userId) {
		Optional<UserDetails> foundById = userDetailsRepository.findById(userId);
		return foundById;
	}

	@GetMapping("/service/getHospitalList/sortByZipCode={zipCode}")
	public List<HospitalDetails> getSortedHospitalListByZipCode(@PathVariable long userId,
			@PathVariable String zipCode) {
		return hospitalDetailsRepository.findByZipCode(zipCode);
	}

	@GetMapping("/service/getHospitalList/sortByLocation={latt},{longg}")
	public SortedMap<Double, HospitalDetails> getSortedHospitalListByLocation(@PathVariable String latt,
			@PathVariable String longg) {

		Double latitude = Double.parseDouble(LocationFormatter.MID.format(Double.parseDouble(latt)));
		Double longitude = Double.parseDouble(LocationFormatter.MID.format(Double.parseDouble(longg)));

		return hospitalDataAccess.getHospitalListSortOnActualDistance(new GeoLocation(latitude, longitude));
	}
}
