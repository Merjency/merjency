package org.subham.home.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.subham.merjency.model.GeoLocation;
import org.subham.merjency.model.resources.HospitalDetails;

public class ModularCSVFileReader {
	File file;
	
	public ModularCSVFileReader(String path) {
		file = new File(path);
		System.out.println(file.exists());
	}

	public List<HospitalDetails> loadFromFile() throws IOException {
		List<HospitalDetails> hospitalDetails = new ArrayList<HospitalDetails>();

		try (Workbook workbook = new XSSFWorkbook(file)) {

			System.out.println(workbook);
			Sheet sheet = workbook.getSheetAt(0);

			Iterator<Row> iterator = sheet.iterator();
			int count = 0;
			while (iterator.hasNext()) {
				++count;
				if (count == 1)
					continue;
				Row row = iterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();
				List<String> cellValues = new ArrayList<>();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					cellValues.add(cell.toString());
				}
				HospitalDetails details = getObject(count, cellValues);
				hospitalDetails.add(details);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return hospitalDetails;
	}

	private HospitalDetails getObject(int id, List<String> row) {
//		System.out.println(row);

		double lattitude = 0.0;
		double longitude = 0.0;

		try {
			lattitude = Double.parseDouble(getDouble(row.get(9)));
			longitude = Double.parseDouble(getDouble(row.get(10)));
		} catch (Exception e) {
			System.out.printf("[ERROR] 	-- LATITUDE AND LONGITUDE CAN'T BE CREATED\n");
		}

		HospitalDetails details = HospitalDetails.builder()
				.addressLine1(row.get(1))
				.addressLine2(row.get(2))
				.addressLine3(row.get(3))
				.hospitalId(id)
				.location(new GeoLocation(lattitude, longitude))
				.state(row.get(5))
				.name(row.get(0))
				.zipCode(row.get(4))
				.phoneNumber1(row.get(6))
				.phoneNumber2(row.get(7))
				.phoneNumber3(row.get(8))
				.build();
		return details;
	}

	private String getDouble(String string) {
		System.out.println(string);
		string = string.trim();
		int endIndex = 0;
		for (int i = 0; i < string.length(); ++i) {
			if ((Character.isDigit(string.charAt(i)) || (string.charAt(i) == '.'))) {
				endIndex++;
			}
		}
		String substring = string.substring(0, endIndex);
//		System.out.println(substring);
		return substring;
	}
}
