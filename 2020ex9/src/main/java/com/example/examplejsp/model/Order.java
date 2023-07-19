package com.example.examplejsp.model;

public class Order {
    private int id;
    private String username;
    private int productId;
    private int quantity;

    public Order(int id, String username, int productId, int quantity) {
        this.id = id;
        this.username = username;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Order(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Order(String username, int productId, int quantity) {
        this.username = username;
        this.productId = productId;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", product_id=" + productId +
                ", quantity=" + quantity +
                '}';
    }
}
