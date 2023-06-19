package com.baettersolutions.baetteridentifier.database;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
    private static String masterdataUrl = "http://localhost:8080/products";

    public static void httpClient(String jsonfilepath) throws IOException {
        try {
            URL url = new URL(masterdataUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            BufferedReader fileReader = new BufferedReader(new FileReader(jsonfilepath));
            StringBuilder jsonPayload = new StringBuilder();
            String line;
            while ((line = fileReader.readLine()) != null) {
                jsonPayload.append(line);
            }
            fileReader.close();

            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(jsonPayload.toString());
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            System.out.println("# Server response code: " + responseCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            System.out.println("# Server response: " + response.toString());

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
