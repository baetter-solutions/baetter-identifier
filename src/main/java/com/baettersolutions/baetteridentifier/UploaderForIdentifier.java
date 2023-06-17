package com.baettersolutions.baetteridentifier;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.Iterator;

public class UploaderForIdentifier {

    public static void showFileWithIterator(XSSFSheet sheet) {
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue() + " | ");
                        break;
                    case STRING:
                        System.out.print(cell.getStringCellValue() + " | ");
                        break;
                    default:
                        System.out.println("Unsupported cell type!");
                        break;
                }
            }
            System.out.println("");
        }
    }
    public static boolean fileIsValid(XSSFWorkbook file) {
        if (file != null) {
            return true;
        } else return false;
    }

}