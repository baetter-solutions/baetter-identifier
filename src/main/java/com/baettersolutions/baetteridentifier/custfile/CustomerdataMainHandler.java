package com.baettersolutions.baetteridentifier.custfile;

import com.baettersolutions.baetteridentifier.controller.Eraser;
import com.baettersolutions.baetteridentifier.masterdata.ConvertExcelMD;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;

public class CustomerdataMainHandler {
    private static final String pathToSaveUserFile = "src/main/resources/outputfiles/customer/";
    private static String pathfinishedfile;

    public static void handlingOfUserdataInput(String filepathToExcelfileFromUser, int custSheetnumber, int custHeadline, int columnWithNumberToIdentify) {
        String newFileName = FilenameUtils.getName(filepathToExcelfileFromUser);
        String newFileNameAndPath = pathToSaveUserFile + newFileName;
        ConvertExcelMD xslxToWorkitem = new ConvertExcelMD();
        XSSFWorkbook userbook = xslxToWorkitem.generateWorksheet(filepathToExcelfileFromUser).getWorkbook();
        XSSFSheet usersheet = xslxToWorkitem.generateUsersheetForWork(userbook, custSheetnumber, custHeadline);
        ConverExcelCF cfFile = new ConverExcelCF();
        XSSFSheet usersheetwithAXRow = cfFile.addRowAXNr(usersheet);
        XSSFSheet filledUsersheet = new Identifier().addAxNr(usersheetwithAXRow, columnWithNumberToIdentify);
        Eraser.deleteFile(filepathToExcelfileFromUser);
        cfFile.createExcelFile(newFileNameAndPath, filledUsersheet);
    }

    public static void setPathfinishedfile(String pathfinishedfile) {
        CustomerdataMainHandler.pathfinishedfile = pathfinishedfile;
    }

    public static String getPathfinishedfile() {
        return pathfinishedfile;
    }
}