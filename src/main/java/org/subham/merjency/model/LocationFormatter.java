package org.subham.merjency.model;

import java.text.DecimalFormat;

public class LocationFormatter {
	/* For 111.11KM */
	public static final DecimalFormat FAR = new DecimalFormat("#");
	/* For 11.111KM */
	public static final DecimalFormat MID = new DecimalFormat("#.#");
	/* For 1.1111KM */
	public static final DecimalFormat CLOSE = new DecimalFormat("#.##");
}
