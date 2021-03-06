package org.subham.merjency.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Embeddable
public class GeoLocation {
	@NotBlank(message = "lattitude is invalid")
	private double lattitude;
	@NotBlank(message = "longitude is invalid")
	private double longitude;
}
