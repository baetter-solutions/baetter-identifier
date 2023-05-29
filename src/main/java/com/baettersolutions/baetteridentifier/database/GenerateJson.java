package com.baettersolutions.baetteridentifier.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.File;
import java.io.IOException;

public class GenerateJson{
    private String outputPath = "src/main/resources/outputfiles/testfiles/testfile.json";



    public void convertToJSON(XSSFSheet filteredSheet, int lineOfHeadline) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode jsonArray = mapper.createArrayNode();

        int lastRowNum = filteredSheet.getLastRowNum();

        for (Row row : filteredSheet){
            ObjectNode jsonRow = mapper.createObjectNode();

            for (Cell cell : row){
                int columnIndex = cell.getColumnIndex();
                String columnName = filteredSheet.getRow(lineOfHeadline).getCell(columnIndex).getStringCellValue();
                String cellValue = "";

                if (cell.getCellType() == CellType.STRING){
                    cellValue = cell.getStringCellValue();
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    cellValue = Double.toString(cell.getNumericCellValue());
                } else if (cell.getCellType() == CellType.BOOLEAN) {
                    cellValue = Boolean.toString(cell.getBooleanCellValue());
                }
                jsonRow.put(columnName, cellValue);
            }
            jsonArray.add(jsonRow);
        }
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputPath), jsonArray);
    }
}
