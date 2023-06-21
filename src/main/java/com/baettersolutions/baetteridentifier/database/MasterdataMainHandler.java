package com.baettersolutions.baetteridentifier.database;

import com.baettersolutions.baetteridentifier.controller.Eraser;

import java.io.IOException;

public class MasterdataMainHandler {
    public static String getJsonFilepath() {
        return jsonFilepath;
    }

    private static String jsonFilepath = "src/main/resources/outputfiles/company/masterdata/masterdata.json";

    public static void handlingOfMasterdataInput(String importFilepathOrigin, int sheetNumber, int lineOfHeadline) throws IOException {
        GenerateJson filehandler = new GenerateJson();
        filehandler.giveConverterTheFile(importFilepathOrigin, sheetNumber, lineOfHeadline);
        HttpClient.httpClient(jsonFilepath);
    }

}
