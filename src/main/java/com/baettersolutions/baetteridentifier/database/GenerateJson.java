package com.baettersolutions.baetteridentifier.database;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.io.File;
import java.io.IOException;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GenerateJson {
    private final Map<String, String> headlineConverter = new HashMap<String, String>() {{
        put("ARTIKELNUMMER", "axnr");
        put("HERSTELLER NAME", "manufacturer");
        put("KURZBESCHREIBUNG", "shortdescription");
        put("HERSTELLER TYPE", "type");
        put("LIEFERANTEN ARTIKELNUMMER", "articlenumber");
        put("POSITIONSRABATT", "rabgroupe");
        put("HERSTELLER", "manufactureridnr");
        put("STATISTIKEINSTANDSPREIS", "ep1");
        put("BRUTTO PREIS", "listprice");
        put("STATUS", "status");
        put("PREISEINHEIT", "priceunit");
        put("EINHEIT VK", "measureunit");
    }};
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
                Cell headlineCell = filteredSheet.getRow(lineOfHeadline).getCell(columnIndex);
                String columnName = headlineConverter.get(headlineCell.getStringCellValue());
                JsonNode cellValue;
                if (cell.getCellType() == CellType.STRING) {
                    cellValue = mapper.valueToTree(cell.getStringCellValue());
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    cellValue = mapper.valueToTree((cell.getNumericCellValue()));
                } else if (cell.getCellType() == CellType.BOOLEAN) {
                    cellValue = mapper.valueToTree(cell.getBooleanCellValue());
                } else {
                    cellValue = mapper.valueToTree(null);
                }
                jsonRow.set(columnName, cellValue);
            }
            jsonArray.add(jsonRow);
        }
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputPath), jsonArray);
    }

    public void giveConverterTheFile(String filepath) throws IOException {
        FilterMasterdata newMasterdataInput = new FilterMasterdata();
        XSSFSheet worksheet = newMasterdataInput.generateWorksheet(filepath, 0, 0);
        System.out.println("JSON file wird erstellt");
        this.convertToJSON(worksheet, 0);

        TransferMasterdata transferMasterdata = new TransferMasterdata();

        System.out.println("Start transferToDatabase");
        transferMasterdata.transferToDatabase(outputPath);
        System.out.println("Transfer finished");

        System.out.println("JSON-File erstellt");
        /*boolean deletionSuccessful = new File(filepath).delete();
        if (deletionSuccessful) {
            System.out.println("Datei gelöscht: " + filepath);
        } else {
            System.out.println("Fehler beim Löschen der Datei: " + filepath);
        }*/

    }
}
