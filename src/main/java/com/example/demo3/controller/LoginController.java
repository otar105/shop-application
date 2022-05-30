package com.example.demo3.controller;

import com.example.demo3.Database;
import com.example.demo3.HelloApplication;
import com.example.demo3.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class LoginController {
    Database d = new Database();
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label warnmessage;
    @FXML
    private Button loginbutton;
    @FXML
    private Button registerbutton;


    public void loginButtonOnAction (ActionEvent event) {
        d.create_table();
        if (username.getText().equals("otoelbakidze2020@gmail.com") && password.getText().equals("testpass2021")) {
            createAdminForm();
        }
        Boolean has_account = d.check_user(username.getText(),password.getText());
        if (has_account) {
            createHomeForm();
        }
        else{
            warnmessage.setText("Incorrect password");
        }
    }
    public void createAccountForm() {
        try {
            Stage stage = (Stage) registerbutton.getScene().getWindow();
            stage.close();
            Stage registerStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("register.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 528, 400);
            registerStage.setTitle("Register");
            registerStage.setScene(scene);
            registerStage.show();
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    public void createAdminForm() {
        try {
            Stage stage = (Stage) registerbutton.getScene().getWindow();
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
    private void createHomeForm() {
        try {
            Stage stage = (Stage)loginbutton.getScene().getWindow();
            stage.close();
            User u = User.getInstance();
            u.setName(username.getText());
            u.setPassword(password.getText());
            Stage registerStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 528, 400);
            registerStage.setTitle("Home");
            stage.setUserData(u);
            registerStage.setScene(scene);
            registerStage.show();
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    @FXML
    private void registerButtonOnAction (ActionEvent event) {
        createAccountForm();
    }
}