package org.subham.merjency.model.resources;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.subham.merjency.model.Gender;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserDetails {

	@Pattern(regexp = "[a-zA-Z_]{5}")
	@NotBlank
	@Id
	private String userName;
	@NotBlank
	private String firstName;
	private String lastName;
	@NotBlank
	private Gender sex;
	@NotBlank
	@Email(message = "email id should be a valid one!!")
	private String emailId;
	@NotBlank
	@Pattern(regexp = "[9876]{1}[0-9]{9}")
	private String mobileNo;
}
