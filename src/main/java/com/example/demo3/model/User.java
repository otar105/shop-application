package com.example.demo3.model;

public class User {
    String name;
    String password;
    String money;
    final static User INSTANCE = new User();
    private User() {}

    public static User getInstance() {
        return INSTANCE;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
