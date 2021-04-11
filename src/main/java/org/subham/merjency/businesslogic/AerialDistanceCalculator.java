package org.subham.merjency.businesslogic;

import org.subham.merjency.model.DistanceUnit;
import org.subham.merjency.model.GeoLocation;

public class AerialDistanceCalculator implements GenericDistanceCalculator {

	@Override
	public double getDistance(GeoLocation source, GeoLocation destination, DistanceUnit distanceUnit) {
		double lattitudeDifference = Math.toRadians(source.getLattitude() - destination.getLattitude());
		double longitudeDifference = Math.toRadians(source.getLongitude() - destination.getLongitude());

		double angleInRadian = Math.sin(lattitudeDifference / 2) * Math.sin(lattitudeDifference / 2)
				+ Math.cos(Math.toRadians(destination.getLattitude())) * Math.cos(Math.toRadians(source.getLattitude()))
						* Math.sin(longitudeDifference / 2) * Math.sin(longitudeDifference / 2);

		double c = 2 * Math.atan2(Math.sqrt(angleInRadian), Math.sqrt(1 - angleInRadian));

		double distanceInKm = EARTH_RADIUS_KM * c;
		if (distanceUnit.equals(DistanceUnit.IN_KM)) {
			return distanceInKm;
		} else {
			return convertKMtoMile(distanceInKm);
		}
	}

	public static strictfp double convertKMtoMile(double distanceInKM) {
		return distanceInKM * KM_TO_MILE_COEFFICENT;
	}
}
