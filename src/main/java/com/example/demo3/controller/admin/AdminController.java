package com.example.demo3.controller.admin;

import com.example.demo3.Database;
import com.example.demo3.HelloApplication;
import com.example.demo3.model.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    Database d = new Database();
    @FXML
    private Button addbutton;
    @FXML
    private TableView<Product> table;
    @FXML
    private TableColumn<Product,String> name;
    @FXML
    private TableColumn<Product,String> price;

    @FXML
    private void productButtonOnAction (ActionEvent event) {
        admin_add();
    }
    private void admin_add() {
        try {
            Stage stage = (Stage) addbutton.getScene().getWindow();
            stage.close();
            Stage registerStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin_add.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 528, 400);
            registerStage.setTitle("admin add");
            registerStage.setScene(scene);
            registerStage.show();
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<Product,String>("price"));
        table.setItems(d.view_products());
        table.setEditable(true);
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        price.setCellFactory(TextFieldTableCell.forTableColumn());

    }

    public void onNameEdit(TableColumn.CellEditEvent<Product, String> userStringCellEditEvent) {
        Product user = table.getSelectionModel().getSelectedItem();
        String old_name = user.getName();
        user.setName(userStringCellEditEvent.getNewValue());
        d.update_name(old_name,user.getName());
    }

    public void onPriceEdit(TableColumn.CellEditEvent<Product, String> userStringCellEditEvent) {
        Product user = table.getSelectionModel().getSelectedItem();
        String old_price = user.getPrice();
        user.setPrice(userStringCellEditEvent.getNewValue());
        d.update_price(old_price,user.getPrice());
    }

    public void buttonRemove(ActionEvent actionEvent) {
        Product user = table.getSelectionModel().getSelectedItem();
        table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
        d.delete_product(user.getName());
    }
}
