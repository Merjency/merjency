package org.subham.merjency.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {
	private String userName;
	private String firstName;
	private String lastName;
	private Gender sex;
	private String emailId;
	private String mobileNo;	
}
