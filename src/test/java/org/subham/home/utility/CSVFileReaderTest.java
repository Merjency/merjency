package org.subham.home.utility;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.subham.merjency.model.resources.HospitalDetails;

class CSVFileReaderTest {

	final String PATH = "C:\\Users\\Admin\\Desktop\\hospital_details_v1.2.xlsx";
	@Test
	void testLoadFromFile() {
//		CSVFileReader fileReader = new CSVFileReader();
		ModularCSVFileReader fileReader = new ModularCSVFileReader(PATH);
		try {
			List<HospitalDetails> loadFromFile = fileReader.loadFromFile();
			loadFromFile.forEach(System.out::println);
			System.out.println(loadFromFile.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
