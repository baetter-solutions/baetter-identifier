package com.baettersolutions.baetteridentifier;

import com.baettersolutions.baetteridentifier.database.GenerateJson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BaetterIdentifierApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(BaetterIdentifierApplication.class, args);

//        String pathToSamlpleMasterdataFile = "src/main/resources/importfiles/testfiles/RND_Std_032023_sample.xlsx";
//        String pathToCustomerFile = "src/main/resources/importfiles/testfiles/fileForImportOneSheet.xlsx";
//        GenerateJson TestInput = new GenerateJson();
//        TestInput.giveConverterTheFile(pathToSamlpleMasterdataFile);

        //        newUserInput.bsFileloader(pathToSamlpleMasterdataFile, sheetToWorkWith);
//        UploaderForIdentifier.showFileWithIterator(newUserInput.bsFileloader(pathToSamlpleMasterdataFile, sheetToWorkWith));
    }
}