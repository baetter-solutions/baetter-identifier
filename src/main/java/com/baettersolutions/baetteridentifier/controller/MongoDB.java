package com.baettersolutions.baetteridentifier.controller;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoDB {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;

    private static String connectionString = "mongodb+srv://mongodbuser:CoDeRsBaY1@cluster4solutions.vc73cl1.mongodb.net/dbidentifier?retryWrites=true&w=majority";
    private static String collectionName = "masterdata";

    private static final Logger logger = LoggerFactory.getLogger(MongoDB.class);


    public String getAxnrByArticlenumber(String articlenumber) {
        try {
            connectionMongo();
            Document query = new Document("articlenumber", articlenumber);
            Document result = collection.find(query).first();
            String axnr = String.valueOf(result.getInteger("axnr"));
            closeConnection();
            return axnr;
        } catch (NullPointerException e) {
            return null;
        }
    }

    public String getAxnrByType(String type) {
        try {
            connectionMongo();
            Document query = new Document("type", type);
            Document result = collection.find(query).first();
            String axnr = String.valueOf(result.getInteger("axnr"));
            closeConnection();
            return axnr;
        } catch (NullPointerException e) {
            return null;
        }
    }

    private void connectionMongo() {
        loggerconfig();
        ConnectionString mongoConnection = new ConnectionString(connectionString);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(mongoConnection)
                .build();
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase(mongoConnection.getDatabase());
        collection = database.getCollection(collectionName);
    }

    private void closeConnection() {
        mongoClient.close();
    }

    private static void loggerconfig() {
        ch.qos.logback.classic.Logger rootLogger = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(ch.qos.logback.classic.Level.WARN);
    }

    public static void testConnection() {
        String testconnection = "mongodb+srv://mongodbuser:CoDeRsBaY1@cluster4solutions.vc73cl1.mongodb.net/";
        MongoClientSettings testsettings = MongoClientSettings.builder().applyConnectionString(new ConnectionString(testconnection)).build();
        System.out.println(testsettings);
        try (MongoClient mongoClient = MongoClients.create(testsettings)) {
            mongoClient.listDatabaseNames().forEach(System.out::println);
            System.out.println("Verbindung erfolgreich hergestellt.");
        } catch (Exception e) {
            System.err.println("Fehler beim Herstellen der Verbindung zur Datenbank: " + e.getMessage());
        }
    }


}
