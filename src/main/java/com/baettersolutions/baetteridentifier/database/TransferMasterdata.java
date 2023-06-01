package com.baettersolutions.baetteridentifier.database;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class TransferMasterdata {
    public void transferJsonFile(String jsonFile) {
        // Setzen Sie die URL, an die das JSON-Datei übermittelt werden soll
        String url = "src/main/resources/outputfiles/testfiles/testfile.json";

        // Erstellen Sie den RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Erstellen Sie den HttpHeaders-Objekt und setzen Sie den Content-Type auf "application/json"
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Erstellen Sie die HttpEntity mit dem JSON-String und den HttpHeaders
        HttpEntity<String> requestEntity = new HttpEntity<>(jsonFile, headers);

        // Senden Sie den HTTP POST-Request mit dem JSON-String
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        // Überprüfen Sie die Antwort
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            System.out.println("JSON-Datei erfolgreich übermittelt.");
        } else {
            System.out.println("Fehler beim Übermitteln der JSON-Datei.");
        }
    }
}

