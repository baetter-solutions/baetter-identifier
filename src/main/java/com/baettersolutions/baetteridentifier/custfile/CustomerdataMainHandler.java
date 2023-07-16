package com.baettersolutions.baetteridentifier.custfile;

import com.baettersolutions.baetteridentifier.controller.Eraser;
import com.baettersolutions.baetteridentifier.masterdata.ConvertExcelMD;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;

public class CustomerdataMainHandler {
    private static String pathfinishedfile = "src/main/resources/outputfiles/customer/";

    public static void setPathfinishedfile(String pathfinishedfile) {
        CustomerdataMainHandler.pathfinishedfile = pathfinishedfile;
    }

    public static String getPathfinishedfile() {
        return pathfinishedfile;
    }

    public static void handlingOfUserdataInput(String filepathToExcelfileFromUser, int custSheetnumber, int custHeadline, int columnWithNumberToIdentify) {
        File path = new File(filepathToExcelfileFromUser);
        String newFileNameAndPath = pathfinishedfile + path.getName();
        ConvertExcelMD xslxToWorkitem = new ConvertExcelMD();
        XSSFWorkbook userbook = xslxToWorkitem.generateWorksheet(filepathToExcelfileFromUser).getWorkbook();
        XSSFSheet usersheet = xslxToWorkitem.generateUsersheetForWork(userbook, custSheetnumber, custHeadline);
        ConverExcelCF cfFile = new ConverExcelCF();
        XSSFSheet usersheetwithAXRow = cfFile.addRowAXNr(usersheet);
        XSSFSheet filledUsersheet = new Identifier().addAxNr(usersheetwithAXRow, columnWithNumberToIdentify);
        cfFile.createExcelFile(newFileNameAndPath, filledUsersheet);

        Eraser.deleteFile(filepathToExcelfileFromUser);
//        cfFile.iteratorsheet(filledUsersheet);

    }
}