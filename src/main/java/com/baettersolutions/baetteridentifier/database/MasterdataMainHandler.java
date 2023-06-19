package com.baettersolutions.baetteridentifier.database;

import java.io.IOException;

public class MasterdataMainHandler{

    public  void handlingOfMasterdataInput(String filePath, int sheetNumber, int lineOfHeadline) throws IOException {
        String jsonFilepath = "src/main/resources/outputfiles/testfiles/testfile.json";
        new GenerateJson().giveConverterTheFile(filePath, sheetNumber, lineOfHeadline);
        new HttpClient().httpClient(jsonFilepath);
    }
}
