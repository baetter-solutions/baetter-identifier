package com.baettersolutions.baetteridentifier.database;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class ConvertFromExcel {

    public XSSFSheet generateWorksheet(String path, int sheetNumber, int lineOfHeadline) {
        try {

//             ToDo: Zu viel in einer Funktion
            FileInputStream inputMasterdata = new FileInputStream(path);
            XSSFWorkbook masterWorkbook = new XSSFWorkbook(inputMasterdata);
            inputMasterdata.close();
            XSSFSheet mastersheet = masterWorkbook.getSheetAt(sheetNumber);

            XSSFSheet filteredSheet = reductionMasterdata(mastersheet, lineOfHeadline);
            XSSFSheet newHeadlines = headlineChanger(filteredSheet);
            XSSFSheet newDatatype = correctDatatype(newHeadlines);
            return newDatatype;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private XSSFSheet reductionMasterdata(XSSFSheet sheet, int lineOfHeadline) {
        String headline_axnr = "ARTIKELNUMMER";
        String headline_manufacturer = "HERSTELLER NAME";
        String headline_shortdescription = "KURZBESCHREIBUNG";
        String headline_type = "HERSTELLER TYPE";
        String headline_articlenumber = "LIEFERANTEN ARTIKELNUMMER";
        String headline_rabgroupe = "POSITIONSRABATT";
        String headline_manufactureridnr = "HERSTELLER";
        String headline_ep1 = "STATISTIKEINSTANDSPREIS";
        String headline_listprice = "BRUTTO PREIS";
        String headline_status = "STATUS";
        String headline_priceunit = "PREISEINHEIT";
        String headline_measureunit = "EINHEIT VK";

        Row headerRow = sheet.getRow(lineOfHeadline);

        int colIndex_axnr = -1;
        int colIndex_manufacturer = -1;
        int colIndex_shortdescription = -1;
        int colIndex_type = -1;
        int colIndex_articlenumber = -1;
        int colIndex_rabgroupe = -1;
        int colIndex_manufactureridnr = -1;
        int colIndex_ep1 = -1;
        int colIndex_listprice = -1;
        int colIndex_status = -1;
        int colIndex_priceunit = -1;
        int colIndex_measureunit = -1;

        for (Cell cell : headerRow) {
            String cellValue = cell.getStringCellValue();

            if (cellValue.equals(headline_axnr)) {
                colIndex_axnr = cell.getColumnIndex();
            } else if (cellValue.equals(headline_manufacturer)) {
                colIndex_manufacturer = cell.getColumnIndex();
            } else if (cellValue.equals(headline_shortdescription)) {
                colIndex_shortdescription = cell.getColumnIndex();
            } else if (cellValue.equals(headline_type)) {
                colIndex_type = cell.getColumnIndex();
            } else if (cellValue.equals(headline_articlenumber)) {
                colIndex_articlenumber = cell.getColumnIndex();
            } else if (cellValue.equals(headline_rabgroupe)) {
                colIndex_rabgroupe = cell.getColumnIndex();
            } else if (cellValue.equals(headline_manufactureridnr)) {
                colIndex_manufactureridnr = cell.getColumnIndex();
            } else if (cellValue.equals(headline_ep1)) {
                colIndex_ep1 = cell.getColumnIndex();
            } else if (cellValue.equals(headline_listprice)) {
                colIndex_listprice = cell.getColumnIndex();
            } else if (cellValue.equals(headline_status)) {
                colIndex_status = cell.getColumnIndex();
            } else if (cellValue.equals(headline_priceunit)) {
                colIndex_priceunit = cell.getColumnIndex();
            } else if (cellValue.equals(headline_measureunit)) {
                colIndex_measureunit = cell.getColumnIndex();
            }
        }

        int[] columnIndexes = {
                colIndex_axnr,
                colIndex_manufacturer,
                colIndex_shortdescription,
                colIndex_type,
                colIndex_articlenumber,
                colIndex_rabgroupe,
                colIndex_manufactureridnr,
                colIndex_ep1,
                colIndex_listprice,
                colIndex_status,
                colIndex_priceunit,
                colIndex_measureunit
        };
        XSSFSheet reducedSheet = createFilteredSheet(sheet, columnIndexes);
        return reducedSheet;
    }

    private XSSFSheet createFilteredSheet(XSSFSheet sourceSheet, int[] columnIndexes) {
        XSSFWorkbook filteredWorkbook = sourceSheet.getWorkbook();
        XSSFSheet filteredSheet = filteredWorkbook.createSheet("SpecificMasterdatasheet");

        for (Row sourceRow : sourceSheet) {
            Row filteredRow = filteredSheet.createRow(sourceRow.getRowNum());

            for (int columnIndex : columnIndexes) {
                Cell sourceCell = sourceRow.getCell(columnIndex);
                Cell filteredCell = filteredRow.createCell(columnIndex);

                if (sourceCell != null) {
                    CellType cellType = sourceCell.getCellType();
                    filteredCell.setCellType(cellType);

                    switch (cellType) {
                        case STRING:
                            filteredCell.setCellValue(sourceCell.getStringCellValue());
                            break;
                        case NUMERIC:
                            filteredCell.setCellValue(sourceCell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            filteredCell.setCellValue(sourceCell.getBooleanCellValue());
                            break;
                    }
                }
            }
        }
        return filteredSheet;
    }

    private XSSFSheet headlineChanger(XSSFSheet sheet) {
        XSSFSheet filteredSheetWithCorrectHeadlines = sheet;
        Row headlineRow = filteredSheetWithCorrectHeadlines.getRow(0);
        for (Cell cell : headlineRow) {
            String cellValue = cell.getStringCellValue();
            if (headlineMap.containsKey(cellValue)) {
                String newHeadline = headlineMap.get(cellValue);
                cell.setCellValue(newHeadline);
            }
        }
        return filteredSheetWithCorrectHeadlines;
    }

    private XSSFSheet correctDatatype(XSSFSheet sheet) {
        for (Row row : sheet) {
            for (Cell cell : row) {
                if (cell.getCellType() == CellType.STRING) {
                    String cellValue = cell.getStringCellValue();
                    String headline = sheet.getRow(0).getCell(cell.getColumnIndex()).getStringCellValue();

                    if (datatypeMap.containsKey(headline)) {
                        Class<?> dataType = datatypeMap.get(headline);
                        try {
                            if (dataType == Integer.class) {
                                int intValue = Integer.parseInt(cellValue);
                                cell.setCellValue(intValue);
                                cell.setCellType(CellType.NUMERIC);
                            } else if (dataType == Double.class) {
                                double doubleValue = Double.parseDouble(cellValue);
                                cell.setCellValue(doubleValue);
                                cell.setCellType(CellType.NUMERIC);
                            } else if (dataType == String.class) {
                                cell.setCellValue(cellValue);
                                cell.setCellType(CellType.STRING);
                            }
                        } catch (NumberFormatException e) {

                        }
                    }
                }
            }
        }

        return sheet;
    }


    private final HashMap<String, String> headlineMap = new HashMap<String, String>() {{
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

    private final HashMap<String, Class> datatypeMap = new HashMap<String, Class>() {{
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
}
