package com.baettersolutions.baetteridentifier.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.text.DecimalFormat;

public class Identifier {
    private String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell != null) {
            switch (cell.getCellType()) {
                case STRING:
                    cellValue = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    double numericValue = cell.getNumericCellValue();
                    DecimalFormat decimalFormat = new DecimalFormat("#");
                    cellValue = decimalFormat.format(numericValue);
                    break;
                default:
                    break;
            }
        }
        return cellValue;
    }

    public String identifyByManufacturArticlenumber(String manufactureNumber) {
        String result = new MongoDB().getAxnrByArticlenumber(manufactureNumber);
        if (result != null){
        System.out.println(result + ": identified");}
            return result;
    }

    public String identifyByManufacturType(String manufacturerType) {
        String result = new MongoDB().getAxnrByType(manufacturerType);
        if (result != null){
            System.out.println(result + ": identified");}
        return result;
    }

    public XSSFSheet addAxNr(XSSFSheet usersheet, int rowToFindValue){
        int lastRow = usersheet.getPhysicalNumberOfRows();
        for (int i = 1; i < lastRow; i++){
            Row currentRow = usersheet.getRow(i);
            Cell targetCell = currentRow.getCell(rowToFindValue);
            Cell axNumToAdd = currentRow.createCell(lastRow+1);


            String targetValue = getCellValue(targetCell);
            if (!targetValue.isEmpty()){
                String axNr = identifyByManufacturArticlenumber(targetValue);
                if (axNr == null){
                    axNr = identifyByManufacturType(targetValue);
                }
                axNumToAdd.setCellValue(axNr);
            } else {axNumToAdd.setCellValue("");}
        }


        return usersheet;
    }


}
