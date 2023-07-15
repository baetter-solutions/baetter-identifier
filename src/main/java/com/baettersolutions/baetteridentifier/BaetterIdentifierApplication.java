package com.baettersolutions.baetteridentifier;

import com.baettersolutions.baetteridentifier.controller.MongoDB;
import com.baettersolutions.baetteridentifier.custfile.CustomerdataMainHandler;
import com.baettersolutions.baetteridentifier.database.MasterdataMainHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BaetterIdentifierApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(BaetterIdentifierApplication.class, args);

//        caseUserdata();
//        testcaseMasterdata();
//        MongoDB.testConnection();
    }

    private static void testcaseMasterdata() throws IOException {
        String testfilepath = "src/main/resources/importfiles/testfiles/";
        String everything100k = "RND_Stammdaten_032023.xlsx";
        String fileWithDel = "RND_Std_032023_sample_problems - gel.xlsx";
        String fileWithMultiProblems = "RND_Std_032023_sample_problems.xlsx";
        String filechoise = testfilepath;
        int choice = 1;
        switch (choice) {
            case 1:
                filechoise = testfilepath + everything100k;
                break;
            case 2:
                filechoise = testfilepath + fileWithDel;
                break;
            case 3:
                filechoise = testfilepath + fileWithMultiProblems;
                break;
            default:
                System.out.println("Ungültige Auswahl");
                break;
        }
        int sheetnumber = 0;
        int headlinecolumn = 0;
        MasterdataMainHandler.handlingOfMasterdataInput(filechoise, sheetnumber, headlinecolumn);
    }

    public static void caseUserdata(String path) {
        String uploadedFilepathFromUser = "src/main/resources/importfiles/testfiles/Preisanfrage Sonepar.xlsx";
        int custSheetnumber = 0;
        int custHeadline = 0;
        int[] columns = {0, 2, 3, 4};
        int columnWithNumberToIdentify = 3;
        CustomerdataMainHandler.handlingOfUserdataInput(path,custSheetnumber,custHeadline, columnWithNumberToIdentify);
    }

}