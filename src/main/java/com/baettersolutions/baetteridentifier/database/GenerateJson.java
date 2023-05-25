package com.baettersolutions.baetteridentifier.database;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class GenerateJson {
    private String outputPath = "";
    public void convertToJSON(XSSFSheet sheet){
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode jsonArray = mapper.createArrayNode();

        for (Row row : sheet){
            ObjectNode jsonRow = mapper.createObjectNode();

            for (Cell cell : row){
                int columnIndex = cell.getColumnIndex();
                String columnName = sheet.getRow(lineOfHeadline).getCell()
            }

        }

    }
}
