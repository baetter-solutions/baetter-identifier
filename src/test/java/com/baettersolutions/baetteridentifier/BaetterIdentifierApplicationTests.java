package com.baettersolutions.baetteridentifier;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class BaetterIdentifierApplicationTests {

// Testlist: Valid File,
// Format ok,
// not empty,
// Headline row
// Hersteller & Artikelnummer (spalte/zeile fix)

	@Test
	void validFileUpload(){
		UploaderForIdentifier.loadFile("Path");
		Assert.assertTrue(UploaderForIdentifier.fileIsValid());
	}

	@Test
	void contextLoads() {
	}

}
