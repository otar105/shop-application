package com.example.demo3.controller.user;

import com.example.demo3.Database;
import com.example.demo3.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class RegisterController {
    Database d = new Database();
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private TextField phone;
    @FXML
    private Label warnmessage;
    @FXML
    private Button registerbutton;
    @FXML
    private Button loginbutton;

    public void RegisterButtonOnAction (ActionEvent event) {
        d.create_table();
        Boolean has_account = d.check_if_exists(username.getText());
        if (has_account) {
            warnmessage.setText("Account with this username already exists!");
        }
        else{
            d.add_user(username.getText(),password.getText(),phone.getText());
            createLoginForm();
        }
    }
    public void loginButtonOnAction (ActionEvent event) {
        createLoginForm();
    }
    private void createLoginForm() {
        try {
            Stage stage = (Stage) loginbutton.getScene().getWindow();
            stage.close();
            Stage registerStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 528, 400);
            registerStage.setTitle("Login");
            registerStage.setScene(scene);
            registerStage.show();
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
