package org.subham.merjency.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HospitalDetails {
	private String name;
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;
	private String zipCode;
	private String state;
	private String phoneNumber1;
	private String phoneNumber2;
	private String phoneNumber3;
	private GeoLocation location;
}
