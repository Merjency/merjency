package org.subham.home.utility;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.subham.merjency.model.resources.HospitalDetails;

class CSVFileReaderTest {

	@Test
	void testLoadFromFile() {
		CSVFileReader fileReader = new CSVFileReader();
		try {
			List<HospitalDetails> loadFromFile = fileReader.loadFromFile(new File("C:\\Users\\Admin\\Desktop\\hospital_details_v1.1.csv"));
			System.out.println(loadFromFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
