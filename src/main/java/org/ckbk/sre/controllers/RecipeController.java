package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.ckbk.sre.model.RecipeList;
import org.ckbk.sre.services.RecipeListService;

public class RecipeController {
    @FXML
    private Text title;
    @FXML
    private Text recipeId;
    @FXML
    private Text author;
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
    public void handleAddToFavAction() {
        RecipeListService.addRecipeList(author.getText(), Integer.parseInt(recipeId.getText()), "", true);
    }

    @FXML
    public void handleAddToToDoAction() {
        RecipeListService.addRecipeList(author.getText(), Integer.parseInt(recipeId.getText()), "toDo", false);
    }

    @FXML
    public void handleAddToNowAction() {
        RecipeListService.addRecipeList(author.getText(), Integer.parseInt(recipeId.getText()), "now", false);
    }

    @FXML
    public void handleAddToDoneAction() {
        RecipeListService.addRecipeList(author.getText(), Integer.parseInt(recipeId.getText()), "done", false);
    }
}
