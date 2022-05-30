package com.example.demo3.model;

public class Chart {
    private String id;
    private String client;
    private String name;
    private String price;

    public Chart(String id,String client, String name, String price) {
        this.id = id;
        this.client = client;
        this.name = name;
        this.price = price;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
