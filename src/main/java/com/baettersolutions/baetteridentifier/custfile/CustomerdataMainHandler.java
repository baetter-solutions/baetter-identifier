package com.baettersolutions.baetteridentifier.custfile;

import com.baettersolutions.baetteridentifier.XSSFsheetIterator;
import com.baettersolutions.baetteridentifier.controller.Identifier;
import com.baettersolutions.baetteridentifier.database.ConvertFromExcel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CustomerdataMainHandler {

    public static void userdatapath(String path) {
    }


    public static void handlingOfUserdataInput(String filepathToExcelfileFromUser, int custSheetnumber, int custHeadline, int[] columns) {
        System.out.println("File located at: " + filepathToExcelfileFromUser);
        ConvertFromExcel xslxToWorkitem = new ConvertFromExcel();
        XSSFWorkbook userbook = xslxToWorkitem.generateWorksheet(filepathToExcelfileFromUser).getWorkbook();
        XSSFSheet usersheet = xslxToWorkitem.generateUsersheetForWork(userbook, custSheetnumber, custHeadline, columns);
        new XSSFsheetIterator().showFileWithIterator(usersheet);
        XSSFSheet usersheetwithAXRow = addRowAXNr(usersheet);
        new XSSFsheetIterator().showFileWithIterator(usersheetwithAXRow);

    }

    public static XSSFSheet addRowAXNr(XSSFSheet usersheet) {
        Row headerRow = usersheet.getRow(0);
        int lastCellNum = headerRow.getLastCellNum();
        Cell headerCell = headerRow.createCell(lastCellNum);
        headerCell.setCellValue("AX Number");
        return usersheet;
    }
}