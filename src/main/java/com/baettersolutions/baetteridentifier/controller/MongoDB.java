package com.baettersolutions.baetteridentifier.controller;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDB {
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> collection;

    private static String connectionString = "mongodb+srv://mongodbuser:CoDeRsBaY1@cluster4solutions.vc73cl1.mongodb.net/dbidentifier?retryWrites=true&w=majority";
    private static String collectionName = "masterdata";



    public int getAxnrByArticlenumber(String articlenumber){
        connectionMongo();
        Document query = new Document("articlenumber", articlenumber);
        Document result = collection.find(query).first();
        int axnr = result.getInteger("axnr");
        closeConnection();
        return axnr;
    }

    private void connectionMongo(){
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
}
