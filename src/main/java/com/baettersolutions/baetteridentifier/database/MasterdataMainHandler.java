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
        System.out.println("Step 1");
        ConvertFromExcel xslxToWorkitem = new ConvertFromExcel();
        System.out.println("Step 2");
        XSSFWorkbook mastabook = xslxToWorkitem.generateWorksheet(importFilepathOrigin).getWorkbook();
        System.out.println("Step 3");
        XSSFSheet mastasheet = xslxToWorkitem.masterdataConversion(mastabook, sheetNumber, lineOfHeadline);
        System.out.println("Step 4");
        GenerateJson filehandler = new GenerateJson();
        filehandler.convertXSSFMasterdataToJSON(mastasheet, lineOfHeadline);
        System.out.println("Step 5");
        HttpClient.httpClient(jsonFilepath);
    }

}
