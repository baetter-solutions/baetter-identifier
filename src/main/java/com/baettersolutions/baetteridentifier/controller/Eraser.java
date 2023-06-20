package com.baettersolutions.baetteridentifier.controller;

import java.io.File;
import java.io.FileNotFoundException;

public class Eraser {
    public static void deleteFile(String filePath) {
        try {
            File file = new File(filePath);

            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("File deleted at: " + filePath);
                } else {
                    System.out.println("Error while deleting: " + filePath);
                }
            } else {
                throw new FileNotFoundException("File not found at: " + filePath);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found at: " + filePath);
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error while deleting: " + filePath);
            e.printStackTrace();
        }
    }
}
