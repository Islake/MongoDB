module com.example.mongodb {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;


    opens com.example.mongodb to javafx.fxml;
    exports com.example.mongodb;
}