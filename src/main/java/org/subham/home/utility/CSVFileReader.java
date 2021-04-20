package org.subham.home.utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.subham.merjency.model.GeoLocation;
import org.subham.merjency.model.resources.HospitalDetails;


/**
 * @author Subham Santra
 *
 */
public class CSVFileReader {
	public List<HospitalDetails> loadFromFile(File file) throws IOException {
		List<HospitalDetails> hospitalDetails = new ArrayList<HospitalDetails>();

		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)))) {

			String readLine;
			for (int i = 0; i < 10; ++i) {
				readLine = bufferedReader.readLine();
				if (i > 0) {
					HospitalDetails object = getObject(i, readLine);
					if (object != null) {
						System.out.printf("[INFO] 	-- NEW [HOSPITAL_DETAILS] CREATED FROM LINE NUMBER [%d]\n", i);
						hospitalDetails.add(object);
					} else {
						System.out.printf("[ERROR] 	-- NEW [HOSPITAL_DETAILS] CANNOT BE CREATED FROM LINE NUMBER [%d]\n", i);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return hospitalDetails;
	}

	private HospitalDetails getObject(int id, String nextLine) {
		String[] row = nextLine.split(",");
		if (row.length == 11) {

			double lattitude = 0.0;
			double longitude = 0.0;

			try {
				lattitude = Double.parseDouble(row[9]);
				longitude = Double.parseDouble(row[10]);
			} catch (Exception e) {
				System.out.printf("[ERROR] 	-- LATITUDE AND LONGITUDE CAN'T BE CREATED\n");
			}

			HospitalDetails details = HospitalDetails.builder()
												.addressLine1(row[1])
												.addressLine2(row[2])
												.addressLine3(row[3])
												.hospitalId(id)
												.location(new GeoLocation(lattitude, longitude))
												.state(row[5])
												.name(row[0])
												.zipCode(row[4])
												.phoneNumber1(row[6])
												.phoneNumber2(row[7])
												.phoneNumber3(row[8])
												.build();
			return details;
		}
		return null;
	}
}
