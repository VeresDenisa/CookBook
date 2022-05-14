package org.ckbk.sre.model;

public class Product {
    private String image;
    private String name;
    private String username;
    private float price;
    private float quantity;

    public Product(String image, String name, String username, float price, float quantity) {
        this.image = image;
        this.name = name;
        this.username = username;
        this.price = price;
        this.quantity = quantity;
    }

    public Product(){

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
