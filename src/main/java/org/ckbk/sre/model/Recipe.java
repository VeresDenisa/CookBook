package org.ckbk.sre.model;

public class Recipe {
    private static int id = 1;
    private final int recipeId;
    private final String name;
    private final User author;
    private final int complexity;
    private final int time;
    private final String image;
    private final String description;
    private int stars;
    private boolean approved;

    public enum TYPE{Breakfast, Lunch, Dinner, Other}
    private final TYPE type;

    public Recipe(String name, User author, int complexity, int time, String image, String description, TYPE type) {
        this.name = name;
        this.author = author;
        this.complexity = complexity;
        this.description = description;
        this.time = time;
        this.image = image;
        this.type = type;
        this.stars = 0;
        this.recipeId = id;
        id++;
        this.approved = false;
    }

    public String getName() {
        return name;
    }

    public User getAuthor() {
        return author;
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

    public int getTime() {
        return time;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
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

    public int getRecipeId() {
        return recipeId;
    }
}
