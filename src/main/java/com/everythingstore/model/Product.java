package com.everythingstore.model;

import java.util.Objects;

public class Product {
    private int id;
    private String category;
    private String artist;
    private String album;
    private String genre;
    private String SKU; //stock keeping unit (a unique code for each product)
    private double price;
    private int quantity;

    public Product() {
    }

    public Product(String category, String artist,
                   String album, String genre, String SKU,
                   double price, int quantity) {
        this.category = category;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.SKU = SKU;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(int id, String category, String artist, String album,
                   String genre, String SKU, double price, int quantity) {
        this.id = id;
        this.category = category;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.SKU = SKU;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

/*    @Override
    public String toString() {
        return "Product: "
                + "\n ID: " + ID + "\n Category: " + category + "\n Artist: " + artist + "\n Album: " + album
                + "\n Genre: " + genre + "\n SKU: " + SKU + "\n Price: " + price + "\n Quantity: " + quantity;
    }*/

    @Override
    public String toString() {
        return "Product{" +
                "ID=" + id +
                ", category='" + category + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", genre='" + genre + '\'' +
                ", SKU='" + SKU + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
