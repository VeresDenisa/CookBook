package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.ckbk.sre.model.Recipe;
import org.ckbk.sre.services.RecipeListService;
import org.ckbk.sre.services.RecipeService;

public class RecipeController {
    @FXML
    private Text title;
    @FXML
    private Text recipeId;
    @FXML
    private Text author;
    @FXML
    private Text type;
    @FXML
    private Text complexity;
    @FXML
    private Text time;
    @FXML
    private Text stars;
    @FXML
    private Text description;
    @FXML
    private Text addMessage;
    @FXML
    private ImageView image;

    @FXML
    public void initialize(int i) {
        Recipe recipe = RecipeService.getRecipe(i);
        title.setText(recipe.getName());
        author.setText(recipe.getAuthor());
        image.setImage(new Image(recipe.getImage()));
        if(recipe.getType() == Recipe.TYPE.Breakfast) type.setText("Breakfast");
        else if(recipe.getType() == Recipe.TYPE.Lunch) type.setText("Lunch");
        else if(recipe.getType() == Recipe.TYPE.Dinner) type.setText("Dinner");
        else type.setText("Other");
        complexity.setText(String.valueOf(recipe.getComplexity()) + " / 5");
        time.setText(String.valueOf(recipe.getTime() / 60) + "h " + String.valueOf(recipe.getTime() % 60) + "m");
        stars.setText(String.valueOf(recipe.getStars()) + " / 5");
        description.setText(recipe.getDescription());
    }

    @FXML
    public void handleAddToFavAction() {
        RecipeListService.addRecipeList(author.getText(), Integer.parseInt(recipeId.getText()), false, false, false, true);
        addMessage.setText("Recipe added to My Favorites Recipes!");
    }

    @FXML
    public void handleAddToToDoAction() {
        RecipeListService.addRecipeList(author.getText(), Integer.parseInt(recipeId.getText()), true, false, false, false);
        addMessage.setText("Recipe added to My To Do Recipes!");
    }

    @FXML
    public void handleAddToNowAction() {
        RecipeListService.addRecipeList(author.getText(), Integer.parseInt(recipeId.getText()), false, true, false, false);
        addMessage.setText("Recipe added to My Doing Now Recipes!");
    }

    @FXML
    public void handleAddToDoneAction() {
        RecipeListService.addRecipeList(author.getText(), Integer.parseInt(recipeId.getText()), false, false, true, false);
        addMessage.setText("Recipe added to My Done Recipes!");
    }
}
