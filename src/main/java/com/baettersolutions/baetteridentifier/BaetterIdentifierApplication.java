package com.baettersolutions.baetteridentifier;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

@SpringBootApplication
public class BaetterIdentifierApplication {

    public static void main(String[] args) {
//        SpringApplication.run(BaetterIdentifierApplication.class, args);
        String pathToFile = "src/main/resources/importfiles/testfiles/fileForImportOneSheet.xlsx";
        int sheetToWorkWith = 0;

        UploaderForIdentifier newUserInput = new UploaderForIdentifier();
        newUserInput.BSFileloader(pathToFile, sheetToWorkWith);
        UploaderForIdentifier.showFileWithIterator(newUserInput.BSFileloader(pathToFile, sheetToWorkWith));

//        BaetterIdentifierApplication oldStyle = new BaetterIdentifierApplication();
//        oldStyle.worksWith(pathToFile, sheetToWorkWith);

    }

    public void worksWith(String path, int sheetnumber){
        try
        {
            FileInputStream file = new FileInputStream(new File(path));

            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(sheetnumber);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                while (cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    //Check the cell type and format accordingly
                    switch (cell.getCellType())
                    {
                        case NUMERIC:
                            System.out.print(cell.getNumericCellValue() + " | ");
                            break;
                        case STRING:
                            System.out.print(cell.getStringCellValue() + " | ");
                            break;
                    }
                }
                System.out.println("");
            }
            file.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}