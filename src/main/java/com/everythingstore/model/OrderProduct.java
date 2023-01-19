package com.everythingstore.model;

public class OrderProduct {

    private int orderProductID;
    private int orderID;
    private Product product;
    private int quantity;

    public OrderProduct() {
    }

    public OrderProduct(int orderID, Product product, int quantity) {
        this.orderID = orderID;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderProduct(int orderProductID, int orderID, Product product, int quantity) {
        this.orderProductID = orderProductID;
        this.orderID = orderID;
        this.product = product;
        this.quantity = quantity;
    }

    public int getOrderProductID() {
        return orderProductID;
    }

    public void setOrderProductID(int orderProductID) {
        this.orderProductID = orderProductID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "orderProductID=" + orderProductID +
                ", orderID=" + orderID +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
