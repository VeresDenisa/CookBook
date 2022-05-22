package org.ckbk.sre.model;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

import java.util.ArrayList;

public class Order {
    @Id
    private NitriteId id;
    private String username;
    private float totalPrice;

    private boolean sent;
    private String date;
    private ArrayList<String> ingredientName;
    private ArrayList<Integer> ingredientQuantity;
    private ArrayList<String> ingredientMeasurement;

    public Order(String username) {
        this.username = username;
        this.sent = false;
        this.date = "21/05/2022";
        this.totalPrice = 0;
        this.ingredientMeasurement = new ArrayList<>();
        this.ingredientName = new ArrayList<>();
        this.ingredientQuantity = new ArrayList<>();
    }

    public Order(){
    }

    public int getIngredientsNumber(){
        return this.ingredientMeasurement.size();
    }

    public void addIngredient(String name, Integer quantity, String measurement) {
        this.ingredientName.add(name);
        this.ingredientQuantity.add(quantity);
        this.ingredientMeasurement.add(measurement);
    }

    public void removeIngredient(String name, Integer quantity, String measurement) {
        this.ingredientName.remove(name);
        this.ingredientQuantity.remove(quantity);
        this.ingredientMeasurement.remove(measurement);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isSent() {
        return sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public ArrayList<String> getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(ArrayList<String> ingredientName) {
        this.ingredientName = ingredientName;
    }

    public ArrayList<Integer> getIngredientQuantity() {
        return ingredientQuantity;
    }

    public void setIngredientQuantity(ArrayList<Integer> ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    public ArrayList<String> getIngredientMeasurement() {
        return ingredientMeasurement;
    }

    public void setIngredientMeasurement(ArrayList<String> ingredientMeasurement) {
        this.ingredientMeasurement = ingredientMeasurement;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
