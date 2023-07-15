package com.baettersolutions.baetteridentifier.controller;

import com.baettersolutions.baetteridentifier.BaetterIdentifierApplication;
import com.baettersolutions.baetteridentifier.custfile.CustomerFileHandler;
import com.baettersolutions.baetteridentifier.custfile.CustomerdataMainHandler;
import com.baettersolutions.baetteridentifier.database.MasterdataMainHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FileUploadController {
    private static final String UPLOAD_FOLDER = "src/main/resources/importfiles/customer";
    private static final String UPLOAD_MASTERDATA = "src/main/resources/importfiles/company/temp_masterinput";
    // Masterdata
    public int sheetNumber;
    public int lineOfHealine;
    // Cust File
    private String custFilePath;
    private final MasterdataController masterdataController;

    @Autowired
    public FileUploadController(MasterdataController masterdataController) {
        this.masterdataController = masterdataController;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String newFileName = fileNameUpload(file);
            Path uploadPath = Paths.get(UPLOAD_FOLDER).toAbsolutePath().normalize();
            String path = uploadPath.resolve(newFileName).toString();
            file.transferTo(new File(path));
            System.out.println(newFileName + " wurde hochgeladen");

            this.custFilePath = path;
            return ResponseEntity.ok(file.getOriginalFilename() + "  wurde hochgeladen");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }

    @PostMapping("/uploadToCustHandler")
    public ResponseEntity<String> uploadToCustHandler(@RequestBody CustomerFileHandler fileHandler) {
        try {
            int custSheetnumber = fileHandler.getCustSheetnumber() - 1;
            int custHeadline = fileHandler.getCustHeadline() - 1;
            int columnWithNumberToIdentify = fileHandler.getColumnWithNumberToIdentify() - 1;
            CustomerdataMainHandler.handlingOfUserdataInput(custFilePath, custSheetnumber, custHeadline, columnWithNumberToIdentify);
            return ResponseEntity.ok("Daten wurden erfolgreich verarbeitet");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error with cust values");
        }
    }

    private String fileNameUpload(MultipartFile file) {
        String timestamp = new SimpleDateFormat("yyMMdd-HHmm").format(new Date());
        String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String newFileName = timestamp + "_" + originalFileName;
        return newFileName;
    }

    @PostMapping("/masterdata")
    public ResponseEntity<String> uploadMasterdata(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Empty file");
        }

        try {
            Path uploadPath = Paths.get(UPLOAD_MASTERDATA).toAbsolutePath().normalize();
            String filePath = uploadPath.resolve(file.getOriginalFilename()).toString();
            file.transferTo(new File(filePath));
            new MasterdataMainHandler().handlingOfMasterdataInput(filePath, sheetNumber, lineOfHealine);
            return ResponseEntity.ok(file.getOriginalFilename() + " wurde hochgeladen und wird nun konvertiert");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }
}