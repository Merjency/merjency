package org.subham.merjency.model;

import org.subham.merjency.model.resources.HospitalDetails;
import org.subham.merjency.model.resources.UserDetails;

public class DummyResourceManager {
	public static UserDetails getDummyUserDetails() {
		return UserDetails.builder()
				.userId(0)
				.name("Subham Santra")
				.mobileNo("7384166784")
				.emailId("sbm_dummy@yahoo.com")
				.pin("85641")
				.sex(Gender.MALE.toString())
				.build();
	}
	
	public static HospitalDetails getDummyHospitalDetails() {
		return HospitalDetails.builder()
				.addressLine1("some street")
				.addressLine2("near some other street")
				.addressLine3("opposite to some location")
				.location(new GeoLocation(22.572646, 88.363895))
				.hospitalId(0)
				.name("Hospital Name")
				.phoneNumber1("7513323215")
				.phoneNumber2("9874513301")
				.phoneNumber3("8523697412")
				.state("State")
				.zipCode("100001")
				.build();
	}
}
