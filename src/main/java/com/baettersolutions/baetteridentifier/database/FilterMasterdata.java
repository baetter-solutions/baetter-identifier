package com.baettersolutions.baetteridentifier.database;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
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

    private XSSFSheet reductionMasterdata(XSSFSheet sheet, int lineOfHeadline) {
        // Headlines
        final String col1_axnr = "ARTIKELNUMMER";
        final String col2_manufacturer = "HERSTELLER NAME";
        final String col3_shortdescription = "KURZBESCHREIBUNG";
        final String col4_type = "HERSTELLER TYPE";
        final String col5_articlenumber = "LIEFERANTEN ARTIKELNUMMER";
        final String col6_rabgroupe = "POSITIONSRABATT";
        final String col7_manufactureridnr = "HERSTELLER";
        final String col8_ep1 = "STATISTIKEINSTANDSPREIS";
        final String col9_listprice = "BRUTTO PREIS";
        final String col10_status = "STATUS";
        final String col11_priceunit = "PREISEINHEIT";
        final String col12_measureunit = "EINHEIT VK";

        // Column numbers
        int colnr_axnr = -1;
        int colnr_manufacturer = -1;
        int colnr_shortdesciption = -1;
        int colnr_type = -1;
        int colnr_arctilenumber = -1;
        int colnr_rabgroupe = -1;
        int colnr_manufactureridnr = -1;
        int colnr_epi1 = -1;
        int colnr_listprice = -1;
        int colnr_status = -1;
        int colnr_priceunit = -1;
        int colnr_measureunit = -1;

        // Get the headline row
        XSSFRow headlineRow = sheet.getRow(lineOfHeadline);
        if (headlineRow != null) {
            int lastCellNum = headlineRow.getLastCellNum();
            for (int col = 0; col < lastCellNum; col++) {
                XSSFCell cell = headlineRow.getCell(col);
                if (cell != null && cell.getCellType() == CellType.STRING) {
                    String cellValue = cell.getStringCellValue();
                    switch (cellValue) {
                        case col1_axnr:
                            colnr_axnr = col;
                            break;
                        case col2_manufacturer:
                            colnr_manufacturer = col;
                            break;
                        case col3_shortdescription:
                            colnr_shortdesciption = col;
                            break;
                        case col4_type:
                            colnr_type = col;
                            break;
                        case col5_articlenumber:
                            colnr_arctilenumber = col;
                            break;
                        case col6_rabgroupe:
                            colnr_rabgroupe = col;
                            break;
                        case col7_manufactureridnr:
                            colnr_manufactureridnr = col;
                            break;
                        case col8_ep1:
                            colnr_epi1 = col;
                            break;
                        case col9_listprice:
                            colnr_listprice = col;
                            break;
                        case col10_status:
                            colnr_status = col;
                            break;
                        case col11_priceunit:
                            colnr_priceunit = col;
                            break;
                        case col12_measureunit:
                            colnr_measureunit = col;
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + cellValue);
                    }
                }
            }
        }

        // Reorder the columns
        XSSFSheet filteredSheet = sheet.getWorkbook().createSheet("SpecificMasterdatasheet");
        for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
            XSSFRow row = sheet.getRow(rowNum);
            if (row != null) {
                XSSFRow newRow = filteredSheet.createRow(rowNum);
                copyCellsByColumnIndex(row, newRow, colnr_axnr);
                copyCellsByColumnIndex(row, newRow, colnr_manufacturer);
                copyCellsByColumnIndex(row, newRow, colnr_shortdesciption);
                copyCellsByColumnIndex(row, newRow, colnr_type);
                copyCellsByColumnIndex(row, newRow, colnr_arctilenumber);
                copyCellsByColumnIndex(row, newRow, colnr_rabgroupe);
                copyCellsByColumnIndex(row, newRow, colnr_manufactureridnr);
                copyCellsByColumnIndex(row, newRow, colnr_epi1);
                copyCellsByColumnIndex(row, newRow, colnr_listprice);
                copyCellsByColumnIndex(row, newRow, colnr_status);
                copyCellsByColumnIndex(row, newRow, colnr_priceunit);
                copyCellsByColumnIndex(row, newRow, colnr_measureunit);
            }
        }

        return filteredSheet;
    }

    private XSSFSheet headlineConverter(XSSFSheet filteredSheet) {
        Row row = filteredSheet.getRow(0);

        for (Cell cell : row) {
            int columnIndex = cell.getColumnIndex();
            Cell headlineCell = filteredSheet.getRow(0).getCell(columnIndex);
            String columnName = headlineConverter.get(headlineCell.getStringCellValue());
            return filteredSheetWithCorrectHeadlines;
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
//             ToDo: Zu viel in einer Funktion
            System.out.println("FileInputStream ...");
            FileInputStream inputMasterdata = new FileInputStream(path);
            System.out.println("... erstelle ein Workbook ...");
            XSSFWorkbook masterWorkbook = new XSSFWorkbook(inputMasterdata);
            inputMasterdata.close();
            System.out.println("... extract worksheet ...");
            XSSFSheet mastersheet = masterWorkbook.getSheetAt(sheetNumber);
            System.out.println("... filtering");
            XSSFSheet finalfilteredSheet = reductionMasterdata(mastersheet, lineOfHeadline);
            System.out.println("... return finalfilteredSheet");
            return finalfilteredSheet;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
