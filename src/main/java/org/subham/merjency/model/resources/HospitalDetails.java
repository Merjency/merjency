package org.subham.merjency.model.resources;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import javax.persistence.Id;

import org.subham.merjency.model.GeoLocation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class HospitalDetails implements Resource {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long hospitalId;

	@NotBlank
	private String name;

	@NotBlank(message = "atleast one line address is needed")
	private String addressLine1;
	private String addressLine2;
	private String addressLine3;

	@NotBlank(message = "must have a valid zipcode")
	private String zipCode;

	@NotBlank(message = "must have a valid state")
	private String state;

	@NotBlank(message = "ateast one phone number is needed")
	private String phoneNumber1;
	private String phoneNumber2;
	private String phoneNumber3;

	private GeoLocation location;

	@Override
	public String toString() {
		return "HospitalDetails [hospitalId=" + hospitalId + ", name=" + name + ", addressLine1=" + addressLine1
				+ ", addressLine2=" + addressLine2 + ", addressLine3=" + addressLine3 + ", zipCode=" + zipCode
				+ ", state=" + state + ", phoneNumber1=" + phoneNumber1 + ", phoneNumber2=" + phoneNumber2
				+ ", phoneNumber3=" + phoneNumber3 + ", location=" + location + "]";
	}
	
	
}
