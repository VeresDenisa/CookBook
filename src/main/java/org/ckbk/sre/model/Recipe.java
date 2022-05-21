package org.ckbk.sre.model;

public class Recipe {
    private String name;
    private String author;
    private int complexity;
    private int time;
    private String image;
    private String description;
    private int stars;
    private boolean approved;

    public enum TYPE{Breakfast, Lunch, Dinner, Other}
    private TYPE type;

    public Recipe(String name, String author, int complexity, int time, String image, String description, TYPE type) {
        this.name = name;
        this.author = author;
        this.complexity = complexity;
        this.description = description;
        this.time = time;
        this.image = image;
        this.type = type;
        this.stars = 0;
        this.approved = false;
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

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public TYPE getType() {
        return type;
    }

}
