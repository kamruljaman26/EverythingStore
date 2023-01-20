package model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Order {

    private int order_id;
    private Customer customer;
    private ArrayList<OrderProduct> products;

    public Order() {
    }

    public Order(Customer customer, ArrayList<OrderProduct> products) {
        this.customer = customer;
        this.products = products;
    }

    public Order(int order_id, Customer customer, ArrayList<OrderProduct> products) {
        this.order_id = order_id;
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

    public ArrayList<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<OrderProduct> products) {
        this.products = products;
    }

    // calculate and return total price
    public int getTotalPrice() {
        AtomicInteger totalPrice = new AtomicInteger();
        products.forEach(p -> {
            totalPrice.addAndGet((int) (p.getQuantity() * p.getProduct().getPrice()));
        });
        return totalPrice.get();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // order details
        sb.append("Order Details").append("\n Order ID:")
                .append(order_id).append(", Customer Details: ").append(customer.toString())
                .append("\n Products: \n");

        final int[] count = {1};
        products.forEach(p -> {
            sb.append("\t").append(count[0]).append(": ").append(p.toString()).append("\n");
            count[0]++;
        });

        return sb.toString();
    }
}
