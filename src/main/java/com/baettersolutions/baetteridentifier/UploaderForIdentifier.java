package com.baettersolutions.baetteridentifier;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class UploaderForIdentifier {

    public XSSFSheet BSFileloader(String filepath, int selectSheetToLoad) {
        try {
            FileInputStream userfile = loadFile(filepath);
            if (fileIsValid(userfile)) {
                XSSFWorkbook userWorkbook = makeWorkbook(userfile);
                workWithSelectedSheet(userWorkbook, selectSheetToLoad);
            } else {
                System.err.println("Die Datei ist ungültig.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void workWithSelectedSheet(XSSFWorkbook workbook, int selectSheetToLoad) {
        System.out.println("[ Tabellenblatt wurde umgewandelt ]");
        workbook.getSheetAt(selectSheetToLoad);
    }

    private XSSFWorkbook makeWorkbook(FileInputStream file) {
        try {
            System.out.println("[ Workbook wird erstellt ]");
            return new XSSFWorkbook(file);
        } catch (IOException e) {
            System.err.println("Ein Fehler ist beim Lesen der Datei aufgetreten.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private FileInputStream loadFile(String path) {
        try {
            System.out.println("[ Datei wird nun geladen. }");
            return new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            System.err.println("Die angegebene Datei wurde nicht gefunden.");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static boolean fileIsValid(FileInputStream file) {
        try (XSSFWorkbook testWorkbook = new XSSFWorkbook(file)) {
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static void showFileWithIterator(XSSFSheet sheet) {
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                switch (cell.getCellType()) {
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue() + " | ");
                        break;
                    case STRING:
                        System.out.print(cell.getStringCellValue() + " | ");
                        break;
                    default:
                        System.out.println("Unsupported cell type!");
                        break;
                }
            }
            System.out.println("");
        }
    }

}