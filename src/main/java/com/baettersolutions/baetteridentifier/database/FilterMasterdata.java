package com.baettersolutions.baetteridentifier.database;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FilterMasterdata {

    private static final String[] COLUMN_NAMES = {
            "col0_axnr", "col1_manufacturer", "col2_shortdesciption", "col3_type", "col4_arctilenumber",
            "col5_rabgroupe", "col6_manufactureridnr", "col7_epi1", "col8_listprice", "col9_status",
            "col10_priceunit", "col11_measureunit"
    };
    private int[] columnNumbers;

    public FilterMasterdata(){
        columnNumbers = new int[COLUMN_NAMES.length];
    }

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
    private final Map<String, Class> headlineDataType = new HashMap<String, Class>() {{
        put("axnr", Integer.class);
        put("manufacturer", String.class);
        put("shortdescription", String.class);
        put("type", String.class);
        put("articlenumber", String.class);
        put("rabgroupe", String.class);
        put("manufactureridnr", Integer.class);
        put("ep1", Double.class);
        put("listprice", Double.class);
        put("status", Integer.class);
        put("priceunit", Integer.class);
        put("measureunit", String.class);
    }};

    private void findColumnNumbers(XSSFSheet sheet, int lineOfHeadline) {
        XSSFRow headlineRow = sheet.getRow(lineOfHeadline);
        if (headlineRow == null) {
            throw new IllegalArgumentException("Headline row not found.");
        }

        for (int col = 0; col < headlineRow.getLastCellNum(); col++) {
            XSSFCell cell = headlineRow.getCell(col);
            if (cell != null) {
                String cellValue = cell.getStringCellValue();
                for (int i = 0; i < COLUMN_NAMES.length; i++) {
                    if (cellValue.equals(COLUMN_NAMES[i])) {
                        columnNumbers[i] = col;
                        break;
                    }
                }
            }
        }

        for (int i = 0; i < COLUMN_NAMES.length; i++) {
            if (columnNumbers[i] == 0) {
                throw new IllegalArgumentException("Column not found: " + COLUMN_NAMES[i]);
            }
        }
    }

    private void copyCellsByColumnIndex(XSSFRow sourceRow, XSSFRow destinationRow, int columnIndex) {
        if (columnIndex >= 0) {
            XSSFCell sourceCell = sourceRow.getCell(columnIndex);
            if (sourceCell != null) {
                XSSFCell newCell = destinationRow.createCell(destinationRow.getLastCellNum());
                newCell.setCellValue(sourceCell.getStringCellValue());
            }
        }
    }

    private XSSFSheet headlineConverter(XSSFSheet filteredSheet) {
        XSSFSheet filteredSheetWithCorrectHeadlines = filteredSheet;
        Row row = filteredSheetWithCorrectHeadlines.getRow(0);

        for (Cell cell : row) {
            int columnIndex = cell.getColumnIndex();
            Cell headlineCell = filteredSheetWithCorrectHeadlines.getRow(0).getCell(columnIndex);
            String columnName = headlineConverter.get(headlineCell.getStringCellValue());
            if (columnName != null) {
                headlineCell.setCellValue(columnName);
            }
        }

        return filteredSheetWithCorrectHeadlines;
    }

    private XSSFSheet adjustColumnDataTypes(XSSFSheet sheet) {
        DataFormatter dataFormatter = new DataFormatter();
        XSSFSheet adjustedSheet = sheet;

        for (Row row : adjustedSheet) {
            for (Cell cell : row) {
                int columnIndex = cell.getColumnIndex();
                Cell headlineCell = adjustedSheet.getRow(0).getCell(columnIndex);
                String columnName = headlineCell.getStringCellValue();
                Class dataType = headlineDataType.get(columnName);
                if (dataType != null) {
                    // Get the cell value as a String and convert it to the appropriate data type
                    String cellValueAsString = dataFormatter.formatCellValue(cell);
                    Object convertedValue = convertToDataType(cellValueAsString, dataType);
                    setCellValueWithType(cell, convertedValue);
                }
            }
        }

        return adjustedSheet;
    }

    private Object convertToDataType(String cellValue, Class dataType) {
        if (dataType == String.class) {
            return cellValue;
        } else if (dataType == Integer.class) {
            return Integer.parseInt(cellValue);
        } else if (dataType == Double.class) {
            return Double.parseDouble(cellValue);
        }

        return null;
    }

    private void setCellValueWithType(Cell cell, Object value) {
        if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        }
    }


    public XSSFSheet generateWorksheet(String path, int sheetNumber, int lineOfHeadline) {
        try {
            FileInputStream inputMasterdata = new FileInputStream(path);
            XSSFWorkbook masterWorkbook = new XSSFWorkbook(inputMasterdata);
            inputMasterdata.close();
            XSSFSheet mastersheet = masterWorkbook.getSheetAt(sheetNumber);
            findColumnNumbers(mastersheet, lineOfHeadline);
            XSSFSheet filteredSheet = reductionMasterdata(mastersheet, lineOfHeadline);
            XSSFSheet sheetWithCorrectHeadlines = headlineConverter(filteredSheet);
            XSSFSheet finalfilteredSheet = adjustColumnDataTypes(sheetWithCorrectHeadlines);
            return finalfilteredSheet;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
