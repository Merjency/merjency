package org.subham.merjency.model.resources;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.subham.merjency.model.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class UserDetails implements Resource {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	@NotBlank
	private String name;
	@ValidateString(acceptedValues = { Gender.FEMALE, Gender.MALE,
			Gender.OTHER }, message = "Allowed only {MALE, FEMALE, OTHER}")
	private String sex;
	@NotBlank
	@Email(message = "email id should be a valid one!!")
	private String emailId;
	@NotBlank
	@Pattern(message = "Valid pin should have 5 digits [only].", regexp = "[0-9]{5}")
	private String pin;
	@Pattern(regexp = "[9876]{1}[0-9]{9}")
	private String mobileNo;
}
