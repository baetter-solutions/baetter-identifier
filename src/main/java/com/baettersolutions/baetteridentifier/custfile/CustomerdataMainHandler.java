package com.baettersolutions.baetteridentifier.custfile;

import com.baettersolutions.baetteridentifier.XSSFsheetIterator;
import com.baettersolutions.baetteridentifier.controller.Identifier;
import com.baettersolutions.baetteridentifier.database.ConvertFromExcel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class CustomerdataMainHandler {

    private static String pathfinishedfile = "src/main/resources/outputfiles/customer/";

    public static void handlingOfUserdataInput(String filepathToExcelfileFromUser, int custSheetnumber, int custHeadline, int[] columns, int columnWithNumberToIdentify) {
        System.out.println("File located at: " + filepathToExcelfileFromUser);
        File path = new File(filepathToExcelfileFromUser);
        String newFileNameAndPath = pathfinishedfile + path.getName();
        System.out.println(newFileNameAndPath);

        ConvertFromExcel xslxToWorkitem = new ConvertFromExcel();
        XSSFWorkbook userbook = xslxToWorkitem.generateWorksheet(filepathToExcelfileFromUser).getWorkbook();
        XSSFSheet usersheet = xslxToWorkitem.generateUsersheetForWork(userbook, custSheetnumber, custHeadline, columns);
        XSSFSheet usersheetwithAXRow = addRowAXNr(usersheet);
        XSSFSheet filledUsersheet = new Identifier().addAxNr(usersheetwithAXRow, columnWithNumberToIdentify);
        createExcelFile(newFileNameAndPath, filledUsersheet);
        iteratorsheet(filledUsersheet);

    }

    public static XSSFSheet addRowAXNr(XSSFSheet usersheet) {
        Row headerRow = usersheet.getRow(0);
        int lastCellNum = headerRow.getLastCellNum();
        Cell headerCell = headerRow.createCell(lastCellNum);
        headerCell.setCellValue("AX-Number");
        return usersheet;
    }

    public static void createExcelFile(String outputPath, XSSFSheet filledUsersheet) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet newSheet = workbook.createSheet(filledUsersheet.getSheetName());
            copySheet(filledUsersheet, newSheet);
            FileOutputStream out = new FileOutputStream(outputPath);
            workbook.write(out);
            workbook.close();
            out.close();
            System.out.println("Excel-Datei wurde erstellt: " + outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void iteratorsheet(XSSFSheet toPrint) {
        new XSSFsheetIterator().showFileWithIterator(toPrint);
    }

    private static void copySheet(XSSFSheet sourceSheet, XSSFSheet targetSheet) {
        for (Row sourceRow : sourceSheet) {
            Row newRow = targetSheet.createRow(sourceRow.getRowNum());
            for (Cell sourceCell : sourceRow) {
                Cell newCell = newRow.createCell(sourceCell.getColumnIndex());
                copyCell(sourceCell, newCell);
            }
        }
    }

    private static void copyCell(Cell sourceCell, Cell newCell) {
        CellType cellType = sourceCell.getCellType();
        switch (cellType) {
            case STRING:
                newCell.setCellValue(sourceCell.getStringCellValue());
                break;
            case NUMERIC:
                newCell.setCellValue(sourceCell.getNumericCellValue());
                break;
            case BOOLEAN:
                newCell.setCellValue(sourceCell.getBooleanCellValue());
                break;
            case FORMULA:
                newCell.setCellValue(sourceCell.getCellFormula());
                break;
            case BLANK:
                newCell.setCellValue("");
                break;
            default:
                break;
        }

    }
}