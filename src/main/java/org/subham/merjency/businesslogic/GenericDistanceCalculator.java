package org.subham.merjency.businesslogic;

import org.subham.merjency.model.DistanceUnit;
import org.subham.merjency.model.GeoLocation;

public interface GenericDistanceCalculator {
	public static final double EARTH_RADIUS_KM = 6371.0;
	public static final double KM_TO_MILE_COEFFICENT = 0.621371;
	
	public double getDistance(GeoLocation source, GeoLocation destination, DistanceUnit distanceUnit);
}
