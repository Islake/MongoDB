package com.example.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;

public class MongoDBCRUD {
    private final MongoCollection<Document> collection;

    public MongoDBCRUD(String dbName, String collectionName) {
        MongoDatabase database = MongoDBUtil.getDatabase(dbName);
        this.collection = database.getCollection(collectionName);
    }
    // Create
    public void createDocument(Document document) {
        collection.insertOne(document);
    }

    // Read
    public Document readDocument(String key, Object value) {
        return collection.find(eq(key, value)).first();
    }

    // Update
    public void updateDocument(String key, Object value, Document updatedDocument) {
        collection.updateOne(eq(key, value), new Document("$set", updatedDocument));
    }
    // Delete
    public void deleteDocument(String key, Object value) {
        collection.deleteOne(eq(key, value));
    }
}
