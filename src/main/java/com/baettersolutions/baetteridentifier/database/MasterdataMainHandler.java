package com.baettersolutions.baetteridentifier.database;

import java.io.IOException;

public class MasterdataMainHandler{

    public static void handlingOfMasterdataInput(String importFilepathOrigin, int sheetNumber, int lineOfHeadline) throws IOException {
        String jsonFilepath = "src/main/resources/outputfiles/testfiles/testfile.json";
        GenerateJson filehandler = new GenerateJson();
        filehandler.giveConverterTheFile(importFilepathOrigin, sheetNumber, lineOfHeadline);
//        filehandler.deleteCachedExcelFile(importFilepathOrigin);
        HttpClient.httpClient(jsonFilepath);
    }
}
