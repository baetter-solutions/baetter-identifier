package com.baettersolutions.baetteridentifier.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class MongoConfiguration {
    private String connectionString;

    public MongoConfiguration() {
        loadConnectionString();
    }

    private void loadConnectionString() {
        Properties properties = new Properties();
        try (FileInputStream inputStream = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(inputStream);
            inputStream.close();
            connectionString = properties.getProperty("spring.data.mongodb.uri");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getConnectionString() {
        return connectionString;
    }
}
