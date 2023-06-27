package com.baettersolutions.baetteridentifier.database;

import com.baettersolutions.baetteridentifier.controller.HttpClient;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;

public class MasterdataMainHandler {
    private static String jsonFilepath = "src/main/resources/outputfiles/company/masterdata/masterdata.json";

    public static String getJsonFilepath() {
        return jsonFilepath;
    }

    public static void handlingOfMasterdataInput(String importFilepathOrigin, int sheetNumber, int lineOfHeadline) throws IOException {
        ConvertFromExcel xslxToWorkitem = new ConvertFromExcel();
        XSSFWorkbook mastabook = xslxToWorkitem.generateWorksheet(importFilepathOrigin).getWorkbook();
        XSSFSheet mastasheet = xslxToWorkitem.masterdataConversion(mastabook, sheetNumber, lineOfHeadline);
        GenerateJson filehandler = new GenerateJson();
        filehandler.convertXSSFMasterdataToJSON(mastasheet, lineOfHeadline);
        HttpClient.httpClient(jsonFilepath);
    }

}
