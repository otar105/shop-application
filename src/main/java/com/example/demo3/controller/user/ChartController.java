package com.example.demo3.controller.user;

import com.example.demo3.Database;
import com.example.demo3.HelloApplication;
import com.example.demo3.model.Chart;
import com.example.demo3.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ChartController implements Initializable {
    Database d = new Database();
    @FXML
    private TableView<Chart> table;
    @FXML
    private TableColumn<Chart,String> name;
    @FXML
    private TableColumn<Chart,String> price;
    @FXML
    private Button remove;
    @FXML
    private Button gobackbutton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User u = User.getInstance();
        name.setCellValueFactory(new PropertyValueFactory<Chart,String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<Chart,String>("price"));
        table.setItems(d.view_chart(u.getName()));
    }
    public void buttonRemove(ActionEvent actionEvent) {
        Chart chart = table.getSelectionModel().getSelectedItem();
        table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
        d.delete_chart(chart.getId());
    }

    public void goback(ActionEvent actionEvent) {
        createHomeForm();
    }
    private void createHomeForm() {
        try {
            Stage stage = (Stage)gobackbutton.getScene().getWindow();
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
