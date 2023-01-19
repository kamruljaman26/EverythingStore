package com.everythingstore.model;

import java.util.ArrayList;

public class Order {

    private int order_id;
    private Customer customer;
    private ArrayList<Product> products;

    public Order(Customer customer, ArrayList<Product> products) {
        this.customer = customer;
        this.products = products;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", customer=" + customer +
                ", products=" + products +
                '}';
    }
}
