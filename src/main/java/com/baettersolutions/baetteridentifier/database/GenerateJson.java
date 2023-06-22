package com.baettersolutions.baetteridentifier.database;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.File;
import java.io.IOException;

public class GenerateJson {

    public void convertXSSFMasterdataToJSON(XSSFSheet sheet, int lineOfHeadline) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode jsonArray = mapper.createArrayNode();
        int lastRowNum = sheet.getLastRowNum();
        for (int rowNum = lineOfHeadline + 1; rowNum <= lastRowNum; rowNum++) {
            Row row = sheet.getRow(rowNum);
            ObjectNode jsonRow = mapper.createObjectNode();
            for (Cell cell : row) {
                int columnIndex = cell.getColumnIndex();
                String columnName = sheet.getRow(lineOfHeadline).getCell(columnIndex).getStringCellValue();
                JsonNode cellValue;
                if (cell.getCellType() == CellType.STRING) {
                    cellValue = mapper.valueToTree(cell.getStringCellValue());
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    if (cell.getNumericCellValue() % 1 == 0) {
                        cellValue = mapper.valueToTree((int) cell.getNumericCellValue());
                    } else {
                        cellValue = mapper.valueToTree(cell.getNumericCellValue());
                    }
                } else if (cell.getCellType() == CellType.BOOLEAN) {
                    cellValue = mapper.valueToTree(cell.getBooleanCellValue());
                } else {
                    cellValue = mapper.valueToTree(null);
                }
                jsonRow.set(columnName, cellValue);
            }
            jsonArray.add(jsonRow);
        }
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(MasterdataMainHandler.getJsonFilepath()), jsonArray);
    }
}
