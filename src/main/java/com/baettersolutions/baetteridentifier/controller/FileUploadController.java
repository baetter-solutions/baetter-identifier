package com.baettersolutions.baetteridentifier.controller;

import com.baettersolutions.baetteridentifier.custfile.CustomerdataMainHandler;
import com.baettersolutions.baetteridentifier.database.MasterdataMainHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    private int sheetNumber;
    private int lineOfHealine;

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String timestamp = new SimpleDateFormat("yyMMdd-HHmm").format(new Date());
            String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String newFileName = timestamp + "_" + originalFileName;
            Path uploadPath = Paths.get(UPLOAD_FOLDER).toAbsolutePath().normalize();
            String filePath = uploadPath.resolve(newFileName).toString();
            file.transferTo(new File(filePath));
            System.out.println(newFileName + " wurde hochgeladen");
            CustomerdataMainHandler newUserfile = new CustomerdataMainHandler();
            newUserfile.userdatapath(filePath);
            return ResponseEntity.ok(file.getOriginalFilename() + "  wurde hochgeladen");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
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
