package com.baettersolutions.baetteridentifier;

import com.baettersolutions.baetteridentifier.database.TransferMasterdata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BaetterIdentifierApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaetterIdentifierApplication.class, args);

/*        String pathToSamlpleMasterdataFile = "src/main/resources/importfiles/testfiles/RND_Std_032023_sample.xlsx";
//        String pathToCustomerFile = "src/main/resources/importfiles/testfiles/fileForImportOneSheet.xlsx";
        int sheetToWorkWith = 0;
        int headline = 0;
//        UploaderForIdentifier newUserInput = new UploaderForIdentifier();
//        newUserInput.bsFileloader(pathToCustomerFile, sheetToWorkWith);
//        UploaderForIdentifier.showFileWithIterator(newUserInput.bsFileloader(pathToCustomerFile, sheetToWorkWith));


    }/*/

    }
}