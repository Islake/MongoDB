package com.example.mongodb;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.bson.Document;
import org.bson.types.ObjectId;

public class Main extends Application {
    private MongoDBCRUD crud;

    @Override
    public void start(Stage primaryStage) {
        crud = new MongoDBCRUD("testdb", "testcollection");

        VBox vbox = new VBox();
        vbox.setSpacing(10);

        // Add new data
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        TextField ageField = new TextField();
        ageField.setPromptText("Age");
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                Document doc = new Document("name", name).append("age", age);
                crud.createDocument(doc);
                System.out.println("Added Person: ID=" + doc.getObjectId("_id") + ", Name=" + name + ", Age=" + age);
                showAlert(Alert.AlertType.INFORMATION, "Success", "Document added successfully!");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to add document: " + ex.getMessage());
            }
        });

        // Read by object ID
        TextField idField = new TextField();
        idField.setPromptText("Object ID");
        Button readButton = new Button("Read");
        TextArea resultArea = new TextArea();
        readButton.setOnAction(e -> {
            try {
                String id = idField.getText();
                Document foundDoc = crud.readDocument("_id", new ObjectId(id));
                if (foundDoc != null) {
                    resultArea.setText(foundDoc.toJson());
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Document found!");
                } else {
                    resultArea.setText("Document not found");
                    showAlert(Alert.AlertType.WARNING, "Not Found", "Document not found");
                }
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to read document: " + ex.getMessage());
            }
        });

        // Delete by object ID
        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            try {
                String id = idField.getText();
                crud.deleteDocument("_id", new ObjectId(id));
                showAlert(Alert.AlertType.INFORMATION, "Success", "Document deleted successfully!");
            } catch (Exception ex) {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to delete document: " + ex.getMessage());
            }
        });

        vbox.getChildren().addAll(nameField, ageField, addButton, idField, readButton, deleteButton, resultArea);

        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("MongoDB JavaFX Application");
        primaryStage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}