package org.subham.merjency.businesslogic;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.subham.merjency.database.HospitalDetailsRepository;
import org.subham.merjency.model.DistanceUnit;
import org.subham.merjency.model.GeoLocation;
import org.subham.merjency.model.resources.HospitalDetails;

@Service
public class HospitalDataAccess {
	@Autowired
	private HospitalDetailsRepository hospitalDetailsRepository;

	GenericDistanceCalculator distanceCalculator = new AerialDistanceCalculator();
	
	public List<HospitalDetails> getHospitalListSortOnZipCode(String zipCode) {
		List<HospitalDetails> foundByZipCode = hospitalDetailsRepository.findByZipCode(zipCode);
		return foundByZipCode;
	}
	
	
	public SortedMap<Double, HospitalDetails> getHospitalListSortOnActualDistance(GeoLocation geoLocation) {
		List<HospitalDetails> foundAll = hospitalDetailsRepository.findAll();
		SortedMap<Double, HospitalDetails> sortedMap = new TreeMap<>();
		
		foundAll.forEach(hospitalDetails -> {
			double distance = distanceCalculator.getDistance(geoLocation, hospitalDetails.getLocation(), DistanceUnit.IN_KM);
			sortedMap.put(distance, hospitalDetails);
		});
		
		return sortedMap;
	}

}
