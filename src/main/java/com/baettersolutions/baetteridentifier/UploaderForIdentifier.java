//package com.baettersolutions.baetteridentifier;
//
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Iterator;
//
//public class UploaderForIdentifier {
//    static FileInputStream file = null;
//    static Workbook workbook = null;
//    public static boolean fileIsValid() {
//        try {
//            workbook = new XSSFWorkbook(file);
//        } catch (IOException e) {
//            return false;
//        }
//        return true;
//    }
//
//    public static void importFile(){
//        XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
//        showFileWithIterator(sheet);
//    }
//
//    public static void loadFile(String path) {
//        try {
//            file = new FileInputStream(new File(path));
//        } catch (FileNotFoundException e) {
//            System.err.println("File not found!");
//        }
//    }
//
//    public static void showFileWithIterator(XSSFSheet sheet){
//        Iterator<Row> rowIterator = sheet.iterator();
//        while (rowIterator.hasNext())
//        {
//            Row row = rowIterator.next();
//            //For each row, iterate through all the columns
//            Iterator<Cell> cellIterator = row.cellIterator();
//
//            while (cellIterator.hasNext())
//            {
//                Cell cell = cellIterator.next();
//                //Check the cell type and format accordingly
//                switch (cell.getCellType())
//                {
//                    case Cell.CELL_TYPE_NUMERIC:
//                        System.out.print(cell.getNumericCellValue() + "t");
//                        break;
//                    case Cell.CELL_TYPE_STRING:
//                        System.out.print(cell.getStringCellValue() + "t");
//                        break;
//                }
//            }
//            System.out.println("");
//        }
//    }
//
//}
