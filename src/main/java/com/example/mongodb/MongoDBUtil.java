package com.example.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static MongoClient mongoClient = null;

    public static MongoDatabase getDatabase(String dbName) {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(CONNECTION_STRING);
        }
        return mongoClient.getDatabase(dbName);
    }

    public static void close() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}