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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    Database d = new Database();
    @FXML
    private TableView<Product> table;
    @FXML
    private TableColumn<Product,String> name;
    @FXML
    private TableColumn<Product,String> price;
    @FXML
    private Label money;
    @FXML
    private Button purchasebutton;

    public void purchaseButtonOnAction (ActionEvent event) {
        User u = User.getInstance();
        String s = money.getText();
        String filtered = "";
        for (int i = 0; i < s.length(); i++) {
            if (!Character.toString(s.charAt(i)).equals("$")) {
                filtered+= s.charAt(i);
            }
        }
        int a = Integer.parseInt(filtered);
        u.setMoney(Integer.toString(a));
        createPurchaseForm();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        User u = User.getInstance();
        name.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
        price.setCellValueFactory(new PropertyValueFactory<Product,String>("price"));
        table.setItems(d.view_products());
        money.setText(d.view_money(u.getName())+"$");
    }

    public void onAddToChart(ActionEvent actionEvent) {
        User u = User.getInstance();
        Product product = table.getSelectionModel().getSelectedItem();
        String s = money.getText();
        String filtered = "";
        for (int i = 0; i < s.length(); i++) {
            if (!Character.toString(s.charAt(i)).equals("$")) {
                filtered+= s.charAt(i);
            }
        }
        int a = Integer.parseInt(filtered);
        a+= Integer.parseInt(d.get_price(product.getPrice()));
        d.add_chart(u.getName(),product.getName(),product.getPrice());
        money.setText(Integer.toString(a)+"$");
    }

    public void viewchart(ActionEvent actionEvent) {
        createChartForm();

    }
    private void createChartForm() {
        try {
            Stage stage = (Stage) purchasebutton.getScene().getWindow();
            stage.close();
            User u = (User) stage.getUserData();
            Stage registerStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("view_chart.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 528, 400);
            registerStage.setTitle("Chart");
            stage.setUserData(u);
            registerStage.setScene(scene);
            registerStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
    private void createPurchaseForm() {
        try {
            Stage stage = (Stage) purchasebutton.getScene().getWindow();
            stage.close();
            Stage registerStage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("purchase.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 528, 400);
            registerStage.setTitle("Thanks");
            registerStage.setScene(scene);
            registerStage.show();
        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }
}
