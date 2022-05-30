module com.example.demo3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.demo3 to javafx.fxml;
    exports com.example.demo3;
    exports com.example.demo3.model;
    opens com.example.demo3.model to javafx.fxml;
    exports com.example.demo3.controller;
    opens com.example.demo3.controller to javafx.fxml;
    exports com.example.demo3.controller.admin;
    opens com.example.demo3.controller.admin to javafx.fxml;
    exports com.example.demo3.controller.user;
    opens com.example.demo3.controller.user to javafx.fxml;
}