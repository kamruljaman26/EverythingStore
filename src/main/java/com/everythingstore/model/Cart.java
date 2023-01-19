package com.everythingstore.model;

import java.util.ArrayList;

public class Cart {
    private ArrayList<Product> cartProducts;

    public Cart() {
        this.cartProducts = new ArrayList<>();
    }

    public Cart(ArrayList<Product> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public ArrayList<Product> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(ArrayList<Product> cartProducts) {
        this.cartProducts = cartProducts;
    }

    // add products
    public void addCartProduct(Product product) {
        if (!cartProducts.contains(product))
            cartProducts.add(product);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartProducts=" + cartProducts +
                '}';
    }
}
