package com.baettersolutions.baetteridentifier.database;

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
        ConvertExcelMD xslxToWorkitem = new ConvertExcelMD();
        System.out.println("- convert from Excel");
        XSSFWorkbook mastabook = xslxToWorkitem.generateWorksheet(importFilepathOrigin).getWorkbook();
        System.out.println("- generate Workbook");
        XSSFSheet mastasheet = xslxToWorkitem.masterdataConversion(mastabook, sheetNumber, lineOfHeadline);
        System.out.println("- generate Worksheet");
        GenerateJson filehandler = new GenerateJson();
        System.out.println("- generate JSON");
        filehandler.convertXSSFMasterdataToJSON(mastasheet, lineOfHeadline);
        System.out.println("- send JSON to Database");
        System.out.println("- temporary file will be deleted");
        Eraser.deleteFile(importFilepathOrigin);
        System.out.println("-- STARTING TRANSFER TO DATABASE - PLEASE WAIT --");
        HttpMasterdataClient.httpPOSTClientForMasterdata(jsonFilepath);
        System.out.println("- all operations done!");
    }

}
