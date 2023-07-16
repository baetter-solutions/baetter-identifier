package com.baettersolutions.baetteridentifier.custfile;

import com.baettersolutions.baetteridentifier.controller.MongoDB;
import com.baettersolutions.baetteridentifier.custfile.ConverExcelCF;
import com.baettersolutions.baetteridentifier.custfile.CustomerdataMainHandler;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.text.DecimalFormat;

public class Identifier {
    public XSSFSheet addAxNr(XSSFSheet usersheet, int rowToFindValue) {
        int lastRow = usersheet.getPhysicalNumberOfRows();
        int counterIdentified = 1;

        MongoDB connection = new MongoDB();
        connection.connectionMongo();
        for (int i = 1; i < lastRow; i++) {
            Row currentRow = usersheet.getRow(i);
            Cell targetCell = currentRow.getCell(rowToFindValue);
            Cell axNumToAdd = currentRow.createCell(ConverExcelCF.getAxRowNumber());


            String targetValue = getCellValue(targetCell);
            if (!targetValue.isEmpty()) {
                String axNr = identifyByManufacturArticlenumber(targetValue);
                if (axNr == null) {
                    axNr = identifyByManufacturType(targetValue);
                }
                if (axNr != null) {
                    int perc = i*100/lastRow;
                    System.out.print("\r"+axNr + " identified - actual Position -> "+ i +" of " + lastRow +" ("+perc+"%)" );
                    counterIdentified++;
                }
                axNumToAdd.setCellValue(axNr);
            } else {
                axNumToAdd.setCellValue("");
            }
        }
        System.out.print("\r"+counterIdentified + " Article identified\n");
        connection.closeConnection();
        return usersheet;
    }

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
        return new MongoDB().getAxnrByArticlenumber(manufactureNumber);
    }

    public String identifyByManufacturType(String manufacturerType) {
        return new MongoDB().getAxnrByType(manufacturerType);
    }
}
