package com.baettersolutions.baetteridentifier.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.io.File;
import java.util.Date;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class FileUploadController {
    private static final String UPLOAD_FOLDER = "src/main/resources/importfiles/uploads/";

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        System.out.println("Blub?");
        try {
            String timestamp = new SimpleDateFormat("yyMMdd-HHmm").format(new Date());
            String originalFileName = StringUtils.cleanPath(file.getOriginalFilename());
            String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
            String newFileName = timestamp + "_" + originalFileName;
            Path uploadPath = Paths.get(UPLOAD_FOLDER).toAbsolutePath().normalize();
            String filePath = uploadPath.resolve(newFileName).toString();
            file.transferTo(new File(filePath));

            System.out.println(newFileName + " wurde hochgeladen");

            // Speichern Sie den Pfad in einer Variable oder geben Sie ihn zurück
            String fileUrl = "/importfiles/uploads/ " + newFileName;

            return ResponseEntity.ok(fileUrl + " Upload successful!!!!!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed");
        }
    }
}
