package com.baettersolutions.baetteridentifier.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("files") MultipartFile[] files) {
        // Hier kannst du den Code zum Verarbeiten der empfangenen Dateien implementieren
        // Speichern auf dem Server, Verarbeitung usw.
        // Rückgabe der geeigneten Antwort, z. B. Erfolgsmeldung oder Fehlermeldung

        return ResponseEntity.ok("Upload successful");
    }
}
