package org.subham.merjency.controller;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.SortedMap;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.subham.merjency.model.GeoLocation;
import org.subham.merjency.model.resources.HospitalDetails;
import org.subham.merjency.model.resources.UserDetails;

/**
 * @author Subham Santra
 *
 */
@RestController
@RequestMapping("/api")
public class CrudController {
//	private final DecimalFormat FAR_FORMATTER = new DecimalFormat("#");
	private final DecimalFormat MID_FORMATTER = new DecimalFormat("#.#");
//	private final DecimalFormat CLOSE_FORMATTER = new DecimalFormat("#.##");

	@Autowired
	private HospitalDetailsRepository hospitalDetailsRepository;

	@Autowired
	private UserDetailsRepository userDetailsRepository;

	private HospitalDataAccess hospitalDataAccess;

	@GetMapping("/test")
	public String testApi() {
		return "WELCOME TO OUR MERJENCY SERVICES";
	}

	@GetMapping("/hospitals")
	public List<HospitalDetails> getHospitals() {
		return hospitalDetailsRepository.findAll();
	}

	@PostMapping("/hospitals/registration")
	public HospitalDetails hospitalRegistration(@RequestBody HospitalDetails hospitalDetails) {
		HospitalDetails savedDetails = hospitalDetailsRepository.save(hospitalDetails);
		return savedDetails;
	}

	@PutMapping("/hospital/{id}")
	public HospitalDetails updateHospitalDetails(@RequestBody HospitalDetails hospitalDetails) {
		return hospitalDetailsRepository.save(hospitalDetails);
	}

	@PostMapping("/users/registration")
	public UserDetails userRegistration(@RequestBody UserDetails userDetails) {
		UserDetails savedDetails = userDetailsRepository.save(userDetails);
		return savedDetails;
	}

	@GetMapping("/users/{userName}")
	public Optional<UserDetails> getUserDetails(@PathVariable String userName) {
		Optional<UserDetails> foundById = userDetailsRepository.findById(userName);
		return foundById;
	}

	@GetMapping("/users/{userName}/hospitalList/sortByZipCode={zipCode}")
	public List<HospitalDetails> getSortedHospitalListByZipCode(@PathVariable String userName,
			@PathVariable String zipCode) {
		return hospitalDetailsRepository.findByZipCode(zipCode);
	}

	@GetMapping("/users/{userName}/hospitalList/sortByLocation={latt},{longg}")
	public SortedMap<Double, HospitalDetails> getSortedHospitalListByLocation(@PathVariable String userName,
			@PathVariable Double latt, @PathVariable Double longg, @PathVariable String circular_limit) {

		latt = Double.parseDouble(MID_FORMATTER.format(latt));
		longg = Double.parseDouble(MID_FORMATTER.format(longg));

		return hospitalDataAccess.getHospitalListSortOnActualDistance(new GeoLocation(latt, longg));
	}
}
