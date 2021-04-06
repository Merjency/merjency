package org.subham.merjency.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DistanceUtilTest {

	final GeoLocation KOLKATA = new GeoLocation(22.572646, 88.363895);
	final GeoLocation DELHI = new GeoLocation(28.704059, 77.10249);
	final GeoLocation DARJEELING = new GeoLocation(27.036007,  88.262672);

	@Test
	void testGetDistanceInKM() {
		System.out.println(DistanceUtil.getDistanceInKM(KOLKATA, DELHI));
		System.out.println(DistanceUtil.getDistanceInKM(KOLKATA, DARJEELING));
	}
	
}
