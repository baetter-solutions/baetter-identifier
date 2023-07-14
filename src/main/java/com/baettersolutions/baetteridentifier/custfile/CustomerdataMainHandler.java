package com.baettersolutions.baetteridentifier.custfile;

import com.baettersolutions.baetteridentifier.XSSFsheetIterator;
import com.baettersolutions.baetteridentifier.controller.Eraser;
import com.baettersolutions.baetteridentifier.controller.Identifier;
import com.baettersolutions.baetteridentifier.database.ConvertExcelMD;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;

public class CustomerdataMainHandler {

    private static String pathfinishedfile = "src/main/resources/outputfiles/customer/";

    public static void setPathfinishedfile(String pathfinishedfile) {
        CustomerdataMainHandler.pathfinishedfile = pathfinishedfile;
    }

    public static void handlingOfUserdataInput(String filepathToExcelfileFromUser, int custSheetnumber, int custHeadline, int[] columns, int columnWithNumberToIdentify) {
        System.out.println("File located at: " + filepathToExcelfileFromUser);
        File path = new File(filepathToExcelfileFromUser);
        String newFileNameAndPath = pathfinishedfile + path.getName();
        System.out.println(newFileNameAndPath);

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