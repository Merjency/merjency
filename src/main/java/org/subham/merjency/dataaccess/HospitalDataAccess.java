package org.subham.merjency.dataaccess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.subham.merjency.database.HospitalRepository;
import org.subham.merjency.model.DistanceUtil;
import org.subham.merjency.model.GeoLocation;
import org.subham.merjency.model.resources.HospitalDetails;

@Service
public class HospitalDataAccess {
	@Autowired
	private HospitalRepository hospitalRepository;

	public DataAccessMethodResponse store(HospitalDetails hospitalDetails) {
		hospitalRepository.add(hospitalDetails);
		return DataAccessMethodResponse.VALID_RESOURCE_INSERTION;
	}

	public ArrayList<HospitalDetails> getList(GeoLocation userLocation, OrderingParam orderOn) {
		ArrayList<HospitalDetails> list = hospitalRepository.getDetails();
		Comparator<HospitalDetails> generatedComparator = generateComparator(userLocation, orderOn);
	
		if (generatedComparator != null) {
			Collections.sort(list, generatedComparator);
		} else {
			System.out.println("INVALID OrderingParam IS USED\nRESULT LIST IS NOT SORTED");
		}
		
		return list;

	}

	private Comparator<HospitalDetails> generateComparator(GeoLocation userLocation, OrderingParam orderOn) {
		if (orderOn.equals(OrderingParam.GPS_LOCATION)) {
			return new Comparator<HospitalDetails>() {
				@Override
				public int compare(HospitalDetails o1, HospitalDetails o2) {
					return Double.compare(DistanceUtil.getDistanceInKM(userLocation, o1.getLocation()),
							DistanceUtil.getDistanceInKM(userLocation, o2.getLocation()));
				}
			};
		} else {
			return null;
		}
	}
}
