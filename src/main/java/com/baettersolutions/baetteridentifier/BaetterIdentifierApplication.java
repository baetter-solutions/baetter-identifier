package com.baettersolutions.baetteridentifier;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaetterIdentifierApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaetterIdentifierApplication.class, args);
//        String pathToFile = "src/main/resources/importfiles/testfiles/fileForImportOneSheet.xlsx";
//        int sheetToWorkWith = 0;

//        UploaderForIdentifier newUserInput = new UploaderForIdentifier();
//        newUserInput.bsFileloader(pathToFile, sheetToWorkWith);
//        UploaderForIdentifier.showFileWithIterator(newUserInput.bsFileloader(pathToFile, sheetToWorkWith));

    }

}