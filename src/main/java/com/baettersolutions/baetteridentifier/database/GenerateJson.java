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
import java.text.DecimalFormat;

public class GenerateJson {
    private String outputPath = "src/main/resources/outputfiles/testfiles/testfile.json";

    public void convertToJSON(XSSFSheet filteredSheet, int lineOfHeadline) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        ArrayNode jsonArray = mapper.createArrayNode();

        int lastRowNum = filteredSheet.getLastRowNum();

        for (int rowNum = lineOfHeadline + 1; rowNum <= lastRowNum; rowNum++) {
            Row row = filteredSheet.getRow(rowNum);
            ObjectNode jsonRow = mapper.createObjectNode();

            for (Cell cell : row) {
                int columnIndex = cell.getColumnIndex();
                String columnName = filteredSheet.getRow(lineOfHeadline).getCell(columnIndex).getStringCellValue();
                String cellValue = "";

                if (cell.getCellType() == CellType.STRING) {
                    cellValue = cell.getStringCellValue();
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    DecimalFormat decimalFormat = new DecimalFormat("#.######");
                    cellValue = decimalFormat.format(cell.getNumericCellValue());
                } else if (cell.getCellType() == CellType.BOOLEAN) {
                    cellValue = Boolean.toString(cell.getBooleanCellValue());
                }
                jsonRow.put(columnName, cellValue);
            }
            jsonArray.add(jsonRow);
        }
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputPath), jsonArray);
    }

    public void giveConverterTheFile(String filepath) throws IOException {
        FilterMasterdata newMasterdataInput = new FilterMasterdata();
        XSSFSheet worksheet = newMasterdataInput.generateWorksheet(filepath, 0,0);
        GenerateJson jsonMasterdate = new GenerateJson();
        jsonMasterdate.convertToJSON(worksheet,0);

        System.out.println("JSON-File erstellt");
        boolean deletionSuccessful = new File(filepath).delete();
        if (deletionSuccessful) {
            System.out.println("Datei gelöscht: " + filepath);
        } else {
            System.out.println("Fehler beim Löschen der Datei: " + filepath);
        }

    }
}
