package com.baettersolutions.baetteridentifier;

import com.baettersolutions.baetteridentifier.custfile.CustomerdataMainHandler;
import com.baettersolutions.baetteridentifier.database.MasterdataMainHandler;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BaetterIdentifierApplication {

    public static void main(String[] args) throws IOException {
//        SpringApplication.run(BaetterIdentifierApplication.class, args);

        testcaseUserdata();
    }

    private static void testcaseMasterdata() throws IOException {
        String testfilepath = "src/main/resources/importfiles/testfiles/RND_Std_032023_sample_single.xlsx";
        int sheetnumber = 0;
        int headlinecolumn = 0;
        MasterdataMainHandler.handlingOfMasterdataInput(testfilepath, sheetnumber, headlinecolumn);
    }

    private static void testcaseUserdata() {
        String uploadedFilepathFromUser = "src/main/resources/importfiles/testfiles/Preisanfrage Sonepar.xlsx";
        int custSheetnumber = 0;
        int custHeadline = 0;
        int[] columns = {0, 2, 3, 4};
        CustomerdataMainHandler.handlingOfUserdataInput(uploadedFilepathFromUser,custSheetnumber,custHeadline,columns);
    }
}