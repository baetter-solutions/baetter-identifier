package com.baettersolutions.baetteridentifier.custfile;

import com.baettersolutions.baetteridentifier.XSSFsheetIterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;


public class ConverExcelCF {
    private static int axRowNumber = -1;

    public static XSSFSheet addRowAXNr(XSSFSheet usersheet) {
        Row headerRow = usersheet.getRow(0);
        axRowNumber = headerRow.getLastCellNum();
        Cell headerCell = headerRow.createCell(axRowNumber);
        headerCell.setCellValue("AX-Number");
        return usersheet;
    }
    public static int getAxRowNumber() {
        return axRowNumber;
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
            CustomerdataMainHandler.setPathfinishedfile(outputPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public static void iteratorsheet(XSSFSheet toPrint) {
        new XSSFsheetIterator().showFileWithIterator(toPrint);
    }
}
