package org.subham.merjency.model;

import java.util.HashMap;
import java.util.Map;

public class DistanceUtil {
	public static final double EARTH_RADIUS_KM = 6371.0;
	public static final double KM_TO_MILE_COEFFICENT = 0.621371;

	public static strictfp Map<DistanceUnit, Double> getDistance(GeoLocation from, GeoLocation to) {
		Map<DistanceUnit, Double> result = new HashMap<>();

		double distanceInKM = getDistanceInKM(from, to);
		double distanceInMile = convertKMtoMile(distanceInKM);

		result.put(DistanceUnit.IN_KM, distanceInKM);
		result.put(DistanceUnit.IN_MILE, distanceInMile);

		return result;

	}

	public static strictfp double getDistanceInKM(GeoLocation location1, GeoLocation location2) {
		double lattitudeDifference = Math.toRadians(location1.getLattitude() - location2.getLattitude());
		double longitudeDifference = Math.toRadians(location1.getLongitude() - location2.getLongitude());

		double angleInRadian = Math.sin(lattitudeDifference / 2) * Math.sin(lattitudeDifference / 2)
				+ Math.cos(Math.toRadians(location2.getLattitude()))
						* Math.cos(Math.toRadians(location1.getLattitude())) * Math.sin(longitudeDifference / 2)
						* Math.sin(longitudeDifference / 2);

		double c = 2 * Math.atan2(Math.sqrt(angleInRadian), Math.sqrt(1 - angleInRadian));
		return EARTH_RADIUS_KM * c;
	}

	public static strictfp double convertKMtoMile(double distanceInKM) {
		return distanceInKM * KM_TO_MILE_COEFFICENT;
	}
}
