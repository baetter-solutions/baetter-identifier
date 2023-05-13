package com.baettersolutions.baetteridentifier;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URL;

//@SpringBootTest
class BaetterIdentifierApplicationTests {

// Testlist: Valid File,
// Format ok,
// not empty,
// Headline row
// Hersteller & Artikelnummer (spalte/zeile fix)

	@Test
	@DisplayName("File has as wrong format or path is wrong")
	public void validFileUpload(){
		UploaderForIdentifier.loadFile("Path");
		Assert.assertTrue(UploaderForIdentifier.fileIsValid());
	}

//	@Test
//	@DisplayName("File contains nothing")
//	public void testEmptyFile() {
//		URL resource = getClass().getClassLoader().getResource("empty.xlsx");
//		Assert.assertTrue(UploaderForIdentifier.loadFile(););
//	}

}
