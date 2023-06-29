package com.baettersolutions.baetteridentifier.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class Identifier {
    public int identifyByManufacturArticlenumber(String manufactureNumber) {
        int result = new MongoDB().getAxnrByArticlenumber(manufactureNumber);
        return result;
    }





    private String getCellValue(XSSFSheet usersheet, int rowToFind, int columnToFind){
        Cell cell = usersheet.getRow(rowToFind).getCell(columnToFind);
        String cellValue = "";
        if (cell != null){
            cellValue = cell.getStringCellValue();
        }
        return cellValue;
    }
}
