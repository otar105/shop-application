package com.example.demo3;
import com.example.demo3.model.Chart;
import com.example.demo3.model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "shotaelbakidze11";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/mziuri";

    public Connection connect(){
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
        }catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }

    public void create_table(){
        try{
            Connection conn = connect();
            Statement statement = conn.createStatement();
            statement.executeUpdate("CREATE TABLE `mziuri`.`users` (`id` INT NOT NULL AUTO_INCREMENT,`username` VARCHAR(45) NOT NULL,`password` VARCHAR(45) NOT NULL,`phone` VARCHAR(45) NOT NULL,PRIMARY KEY (`id`));");
        } catch(SQLException e){
            System.out.println(e);
        }
    }
    public Boolean check_user(String username,String password){
        try{
            Boolean is_user = false;
            Connection conn = connect();
            Statement statement = conn.createStatement();
            ResultSet re = statement.executeQuery("select * from users;");
            while (re.next()) {
                if (username.equals(re.getString(2)) && password.equals(re.getString(3))){
                    is_user = true;
                }
            }
            return is_user;
        } catch(SQLException e){
            System.out.println(e);
            return false;
        }
    }
    public Boolean check_if_exists(String phone){
        try{
            Connection conn = connect();
            Statement statement = conn.createStatement();
            ResultSet re = statement.executeQuery("select * from users;");
            while (re.next()) {
                if (phone.equals(re.getString(1))){
                    return true;
                }
            }
            return false;
        } catch(SQLException e){
            System.out.println(e);
            return false;
        }
    }
    public void add_user(String name,String password,String phone){
        try{
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement("insert into users(username,password,phone) values(?,?,?)");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,phone);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            System.out.println(e);
        }
    }
    public void update_name(String old_name,String new_name){
        try{
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement("update products set name=? where name=?");
            preparedStatement.setString(1,new_name);
            preparedStatement.setString(2,old_name);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            System.out.println(e);
        }
    }
    public void update_price(String old_price,String new_price){
        try{
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement("update products set price=? where price=?");
            preparedStatement.setString(1,new_price);
            preparedStatement.setString(2,old_price);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            System.out.println(e);
        }
    }
    public void add_product(String name,String price){
        try{
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement("insert into products(name,price) values(?,?)");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,price);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            System.out.println(e);
        }
    }
    public void delete_product(String name){
        try{
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement("delete from products where name = ?");
            preparedStatement.setString(1,name);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            System.out.println(e);
        }
    }
    public ObservableList<Product> view_products(){
        ObservableList<Product> list = FXCollections.observableArrayList();
        try{
            Connection conn = connect();
            Statement statement = conn.createStatement();
            ResultSet re = statement.executeQuery("select * from products;");
            while(re.next()){
                list.add(new Product(re.getString(2),re.getString(3)));
            }
            return list;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public String get_price(String name) {
        try{
            Boolean is_user = false;
            Connection conn = connect();
            Statement statement = conn.createStatement();
            ResultSet re = statement.executeQuery("select * from users where name=" +name);
            while (re.next()) {
                return re.getString(3);
            }
        } catch(SQLException e){
            System.out.println(e);
        }
        return name;
    }
    public void add_chart(String client,String name,String price){
        try{
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement("insert into chart(client,name,price) values(?,?,?)");
            preparedStatement.setString(1,client);
            preparedStatement.setString(2,name);
            preparedStatement.setString(3,price);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            System.out.println(e);
        }
    }
    public ObservableList<Chart> view_chart(String client){
        ObservableList<Chart> list = FXCollections.observableArrayList();
        try{
            Connection conn = connect();
            Statement statement = conn.createStatement();
            ResultSet re = statement.executeQuery("select * from chart where client="+"'"+client+"'");
            while(re.next()){
                list.add(new Chart(re.getString(1),re.getString(2),re.getString(3),re.getString(4)));
            }
            return list;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void delete_chart(String name){
        try{
            Connection conn = connect();
            PreparedStatement preparedStatement = conn.prepareStatement("delete from chart where id = ?");
            preparedStatement.setString(1,name);
            preparedStatement.executeUpdate();
        } catch(SQLException e){
            System.out.println(e);
        }
    }
    public String view_money(String client){
        try{
            int money = 0;
            Connection conn = connect();
            Statement statement = conn.createStatement();
            ResultSet re = statement.executeQuery("select * from chart where client="+"'"+client+"'");
            while(re.next()){
                money+=Integer.parseInt(re.getString(4));
            }
            return Integer.toString(money);
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public String view_chart_string(String client){
        ObservableList<Chart> list = FXCollections.observableArrayList();
        try{
            String products = "";
            Connection conn = connect();
            Statement statement = conn.createStatement();
            ResultSet re = statement.executeQuery("select * from chart where client="+"'"+client+"'");
            while(re.next()){
                products += re.getString(3)+" ";
            }
            return products;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public void delete_charts(String name){
        try{
            Connection conn = connect();
            Statement statement = conn.createStatement();
            statement.executeUpdate("delete from chart where client = '"+name+"'");
        } catch(SQLException e){
            System.out.println(e);
        }
    }

}