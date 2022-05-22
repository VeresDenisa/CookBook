package org.ckbk.sre.model;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

import java.util.ArrayList;

public class Recipe {
    @Id
    private NitriteId id;
    private String name;
    private String author;
    private int complexity;
    private int time;
    private String image;
    private String description;
    private boolean approved;

    public enum TYPE{Breakfast, Lunch, Dinner, Other}
    private TYPE type;

    private ArrayList<String> ingredientName;
    private ArrayList<Integer> ingredientQuantity;
    private ArrayList<String> ingredientMeasurement;

    public Recipe(String name, String author, int complexity, int time, String image, String description, TYPE type) {
        this.name = name;
        this.author = author;
        this.complexity = complexity;
        this.description = description;
        this.time = time;
        this.image = image;
        this.type = type;
        this.approved = false;
        this.ingredientMeasurement = new ArrayList<>();
        this.ingredientName = new ArrayList<>();
        this.ingredientQuantity = new ArrayList<>();
    }

    public void addMeasurement(String ceva){
        this.ingredientMeasurement.add(ceva);
    }

    public void addQuantity(Integer ceva){
        this.ingredientQuantity.add(ceva);
    }

    public void addName(String ceva){
        this.ingredientName.add(ceva);
    }

    public Recipe(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public int getComplexity() {
        return complexity;
    }

    public void setComplexity(int complexity) {
        this.complexity = complexity;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public TYPE getType() {
        return type;
    }

}
