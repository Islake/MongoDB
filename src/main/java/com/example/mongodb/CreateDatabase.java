package com.example.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class CreateDatabase {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017";

    public static void main(String[] args) {
        MongoClient mongoClient = MongoClients.create(CONNECTION_STRING);
        MongoDatabase database = mongoClient.getDatabase("testdb");
        System.out.println("Database created: " + database.getName());
        mongoClient.close();
    }
}