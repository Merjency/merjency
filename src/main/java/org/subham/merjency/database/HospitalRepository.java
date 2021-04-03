package org.subham.merjency.database;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
import org.subham.merjency.model.HospitalDetails;

@Component
public class HospitalRepository {
	private ArrayList<HospitalDetails> list = new ArrayList<>();
	
	public void add(HospitalDetails hospitalDetails) {
		list.add(hospitalDetails);
	}
	
	public ArrayList<HospitalDetails> getDetails() {
		return new ArrayList<>(list);
	}
}
