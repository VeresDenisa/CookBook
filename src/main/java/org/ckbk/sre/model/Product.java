package org.ckbk.sre.model;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

public class Product {
    @Id
    private NitriteId id;
    private String image;
    private String name;
    private String username;
    private float price;
    private float quantity;
    private String measurement;
    private float available;

    public Product(String name, String image, String username, float quantity, float price, String measurement, float available) {
        this.image = image;
        this.name = name;
        this.username = username;
        this.price = price;
        this.quantity = quantity;
        this.measurement = measurement;
        this.available = available;
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

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }

    public float getAvailable() {
        return available;
    }

    public void setAvailable(float available) {
        this.available = available;
    }
}
