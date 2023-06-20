package com.baettersolutions.baetteridentifier;

import com.baettersolutions.baetteridentifier.database.MasterdataMainHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BaetterIdentifierApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(BaetterIdentifierApplication.class, args);

//        testcase();
    }

    private static void testcase() throws IOException {
        String testfilepath = "src/main/resources/importfiles/testfiles/RND_Std_032023_sample_single.xlsx";
        int sheetnumber = 0;
        int headlinecolumn = 0;
        MasterdataMainHandler.handlingOfMasterdataInput(testfilepath, sheetnumber, headlinecolumn);
    }


}