package com.example.demo3.controller.admin;

import com.example.demo3.Database;
import com.example.demo3.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AdminAddController {
    Database d = new Database();
    @FXML
    private TextField name;
    @FXML
    private TextField price;
    @FXML
    private Button addbutton;


    public void addButtonOnAction (ActionEvent event) {
        d.add_product(name.getText(),price.getText());
        createAdminForm();
    }
    public void backButtonOnAction (ActionEvent event) {
        createAdminForm();
    }
    public void createAdminForm() {
        try {
            Stage stage = (Stage) addbutton.getScene().getWindow();
            stage.close();
            Stage registerStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 528, 400);
            registerStage.setTitle("admin");
            registerStage.setScene(scene);
            registerStage.show();
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
