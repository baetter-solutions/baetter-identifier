package com.baettersolutions.baetteridentifier;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

@SpringBootApplication
public class BaetterIdentifierApplication {

	public static void main(String[] args) {
//		SpringApplication.run(BaetterIdentifierApplication.class, args);

//		UploaderForIdentifier.loadFile("resources\\testfiles\\fileForImportOneSheet.xlsx");

		try
		{
			FileInputStream file = new FileInputStream(new File("src/main/resources/importfiles/testfiles/fileForImportOneSheet.xlsx"));

			//Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(file);

			//Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0);

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
							System.out.print(cell.getNumericCellValue() + "t");
							break;
						case STRING:
							System.out.print(cell.getStringCellValue() + "t");
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
