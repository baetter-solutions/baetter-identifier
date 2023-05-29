package com.baettersolutions.baetteridentifier;

import com.baettersolutions.baetteridentifier.database.FilterMasterdata;
import com.baettersolutions.baetteridentifier.database.GenerateJson;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BaetterIdentifierApplication {

    public static void main(String[] args) {
//        SpringApplication.run(BaetterIdentifierApplication.class, args);

        String pathToSamlpleMasterdataFile = "src/main/resources/importfiles/testfiles/RND_Std_032023_sample.xlsx";
        String pathToCustomerFile = "src/main/resources/importfiles/testfiles/fileForImportOneSheet.xlsx";
        int sheetToWorkWith = 0;
        int headline = 0;
//        UploaderForIdentifier newUserInput = new UploaderForIdentifier();
//        newUserInput.bsFileloader(pathToCustomerFile, sheetToWorkWith);
//        UploaderForIdentifier.showFileWithIterator(newUserInput.bsFileloader(pathToCustomerFile, sheetToWorkWith));


        FilterMasterdata newMasterdataInput = new FilterMasterdata();
        XSSFSheet worksheet = newMasterdataInput.generateWorksheet(pathToSamlpleMasterdataFile, sheetToWorkWith, headline);
//        UploaderForIdentifier.showFileWithIterator(newMasterdataInput.generateWorksheet(pathToSamlpleMasterdataFile,sheetToWorkWith, headline));
        GenerateJson jsonfile = new GenerateJson();
        try {
            jsonfile.convertToJSON(worksheet, headline);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}