package org.ckbk.sre.model;

import org.dizitart.no2.NitriteId;
import org.dizitart.no2.objects.Id;

public class RecipeList {
    @Id
    private NitriteId id;
    private String username;
    private int recipeId;
    private boolean done;
    private boolean toDo;
    private boolean now;
    private boolean fav;

    public RecipeList(String username, int recipeId, boolean fav, boolean toDo, boolean now, boolean done) {
        this.username = username;
        this.recipeId = recipeId;
        this.fav = fav;
        this.toDo = toDo;
        this.now = now;
        this.done = done;
    }

    public RecipeList(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isToDo() {
        return toDo;
    }

    public void setToDo(boolean toDo) {
        this.toDo = toDo;
    }

    public boolean isNow() {
        return now;
    }

    public void setNow(boolean now) {
        this.now = now;
    }

    public boolean isFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }
}
