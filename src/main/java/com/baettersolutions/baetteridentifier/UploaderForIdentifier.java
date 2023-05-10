package com.baettersolutions.baetteridentifier;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UploaderForIdentifier {
    static FileInputStream file = null;
    static Workbook workbook = null;
    public static boolean fileIsValid() {
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static void loadFile(String path) {
        try {
            file = new FileInputStream(new File(path));
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
        }
    }
}
