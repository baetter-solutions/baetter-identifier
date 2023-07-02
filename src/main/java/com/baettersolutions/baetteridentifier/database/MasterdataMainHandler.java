package com.baettersolutions.baetteridentifier.database;

import com.baettersolutions.baetteridentifier.XSSFsheetIterator;
import com.baettersolutions.baetteridentifier.controller.Eraser;
import com.baettersolutions.baetteridentifier.controller.HttpMasterdataClient;
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
        System.out.println("- convert from Excel");
        XSSFWorkbook mastabook = xslxToWorkitem.generateWorksheet(importFilepathOrigin).getWorkbook();
        System.out.println(" - generate Workbook");
        XSSFSheet mastasheet = xslxToWorkitem.masterdataConversion(mastabook, sheetNumber, lineOfHeadline);
        System.out.println("  - generate Worksheet");

        XSSFsheetIterator.showFileWithIterator(mastasheet);

        GenerateJson filehandler = new GenerateJson();
        System.out.println("   - generate JSON");
        filehandler.convertXSSFMasterdataToJSON(mastasheet, lineOfHeadline);
        System.out.println("    - send JSON to Database");
        Eraser.deleteFile(importFilepathOrigin);
        HttpMasterdataClient.httpPOSTClientForMasterdata(jsonFilepath);
        System.out.println("     - all operations done!");
    }

}
