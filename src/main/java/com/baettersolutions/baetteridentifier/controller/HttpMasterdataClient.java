package com.baettersolutions.baetteridentifier.controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpMasterdataClient {
    private static String masterdataUrl = "http://localhost:8080/products";

    public static void httpPOSTClientForMasterdata(String jsonfilepath) {
        try {
            URL url = new URL(masterdataUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);

            BufferedReader fileReader = new BufferedReader(new FileReader(jsonfilepath));
            StringBuilder jsonPayload = new StringBuilder();
            String line = null;
            while ((line = fileReader.readLine()) != null) {
                jsonPayload.append(line);
            }
            fileReader.close();

            byte[] input = jsonPayload.toString().getBytes("utf-8");
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(input, 0, input.length);
            outputStream.flush();
            outputStream.close();

            int responseCode = connection.getResponseCode();
            System.out.println("# Server response code: " + responseCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
                System.out.println("# Server response: " + response.toString());
            }
            reader.close();

            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void httpTestClient() {

        try {
            URL url = new URL(masterdataUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);

            String jsonString = "[{\"axnr\": 123456789,\"type\": \"3MMM\",\"shortdescription\": \"Spiralband,Bündeldurchm.b.50,8mm,PE,orange,30,5m\",\"manufactureridnr\": 402000,\"manufacturer\": \"PANDUIT\",\"rabgroupe\": \"DZ.11\",\"articlenumber\": \"T25F-C3Y\",\"listprice\": 63.18,\"ep1\": 36.861111111111114,\"measureunit\": \"ROL\",\"priceunit\": 1,\"status\": 0}]";
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println(response.toString());
            }
            System.out.println("Finish");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
