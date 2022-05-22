package org.ckbk.sre.model;

import org.ckbk.sre.services.OrderService;
import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

import java.util.ArrayList;
import java.util.Objects;

public class Order {
    @Id
    private NitriteId id;
    private String username;
    private String shop;
    private float totalPrice;

    private boolean sent;
    private String date;
    private ArrayList<Product> ingredient;
    private ArrayList<Integer> ingredientQuantity;

    public Order(String username, String shop, boolean sent, ArrayList<Product> ingredient, ArrayList<Integer> ingredientQuantity) {
        this.shop = shop;
        this.username = username;
        this.sent = sent;
        this.date = "21/05/2022";
        this.ingredient = ingredient;
        this.ingredientQuantity = ingredientQuantity;
        this.totalPrice = 0;
        calculateTotalPrice();
    }

    public Order(){
    }

    public int getIngredientsNumber(){
        return this.ingredient.size();
    }

    public void addIngredient(Product product, Integer quantity) {
        for(int i = 0; i < this.ingredient.size(); i++)
            if(Objects.equals(this.ingredient.get(i), product)) {
                this.ingredientQuantity.set(i, this.ingredientQuantity.get(i) + quantity);
                return;
            }
        this.ingredient.add(product);
        this.ingredientQuantity.add(quantity);
    }

    private void calculateTotalPrice(){
        this.totalPrice = 0;
        for(int i = 0; i < this.ingredient.size(); i++) {
            if (this.ingredient.get(i) != null && this.ingredientQuantity.get(i) != null)
                this.totalPrice += this.ingredient.get(i).getPrice() * (this.ingredientQuantity.get(i) / this.ingredient.get(i).getQuantity());
        }
    }

    public void removeIngredient(Product product, Integer quantity) {
        this.ingredient.remove(product);
        this.ingredientQuantity.remove(quantity);
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

    public String getIngredientQuantityString() {
        String s = "";
        for(int i = 0; i < ingredientQuantity.size(); i++)
            s += ingredientQuantity.get(i) + "\n";
        return s;
    }

    public String getIngredientNameString() {
        String s = "";
        for(int i = 0; i < ingredient.size(); i++) {
            System.out.println(ingredient.get(i));
            s += ingredient.get(i).getName() + "\n";
        }
        return s;
    }

    public String getIngredientPriceString() {
        String s = "";
        for(int i = 0; i < ingredient.size(); i++)
            s += ingredient.get(i).getPrice() + "\n";
        return s;
    }

    public void setIngredientQuantity(ArrayList<Integer> ingredientQuantity) {
        this.ingredientQuantity = ingredientQuantity;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getTotalPrice() {
        this.calculateTotalPrice();
        return this.totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }
}
