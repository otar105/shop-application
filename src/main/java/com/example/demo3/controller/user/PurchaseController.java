package com.example.demo3.controller.user;

import com.example.demo3.Database;
import com.example.demo3.HelloApplication;
import com.example.demo3.model.Product;
import com.example.demo3.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class PurchaseController implements Initializable {
    Database d = new Database();
    @FXML
    private Label products;
    @FXML
    private Label money;
    @FXML
    private Button gobackbutton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User u = User.getInstance();
        money.setText(u.getMoney()+"$");
        products.setText(d.view_chart_string(u.getName()));
        d.delete_charts(u.getName());
    }

    public void goback(ActionEvent actionEvent) {
        createHomeForm();
    }
    private void createHomeForm() {
        try {
            Stage stage = (Stage) gobackbutton.getScene().getWindow();
            stage.close();
            Stage registerStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("home.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 528, 400);
            registerStage.setTitle("Home");
            registerStage.setScene(scene);
            registerStage.show();
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
