package com.baettersolutions.baetteridentifier;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.text.DecimalFormat;
import java.util.Iterator;

public class XSSFsheetIterator {

    public static void showFileWithIterator(XSSFSheet sheet) {
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case NUMERIC:
                        double numericValue = cell.getNumericCellValue();
                        DecimalFormat decimalFormat = new DecimalFormat("0");
                        int intValue = (int) numericValue;
                        if (numericValue == intValue) {
                            System.out.print(intValue + " | ");
                        } else {
                            String formattedValue = decimalFormat.format(numericValue);
                            System.out.print(formattedValue + " | ");
                        }
                        break;
                    case STRING:
                        System.out.print(cell.getStringCellValue() + " | ");
                        break;
                    case BLANK:
                        System.out.print("     | ");
                        break;
                    default:
                        System.out.println("Unsupported cell type!");
                        break;
                }
            }
            System.out.println("");
        }
    }

}