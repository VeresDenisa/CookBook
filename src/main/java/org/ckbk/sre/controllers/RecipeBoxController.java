package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.ckbk.sre.model.Recipe;
import org.ckbk.sre.model.RecipeList;
import org.ckbk.sre.model.User;
import org.ckbk.sre.services.RecipeService;
import org.dizitart.no2.NitriteCollection;
import org.dizitart.no2.objects.ObjectRepository;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class RecipeBoxController {
    @FXML
    public Text type;
    @FXML
    private Text title;
    @FXML
    private Text author;
    @FXML
    private Text recipeId;
    @FXML
    private Text complexity;
    @FXML
    private Text time;
    @FXML
    private Text stars;
    @FXML
    private Text image;

    @FXML
    public void initialize() {
//        Recipe recipe = RecipeService.getRecipe(0);
//        title.setText(recipe.getName());
//        recipeId.setText(String.valueOf(recipe.getRecipeId()));
//        author.setText(recipe.getAuthor().getUsername());
//        if(recipe.getType() == Recipe.TYPE.Breakfast) type.setText("Breakfast");
//        else if(recipe.getType() == Recipe.TYPE.Lunch) type.setText("Lunch");
//        else if(recipe.getType() == Recipe.TYPE.Dinner) type.setText("Dinner");
//        else type.setText("Other");
//        complexity.setText(String.valueOf(recipe.getComplexity()) + " / 5");
//        time.setText(String.valueOf(recipe.getTime() / 60) + "h " + String.valueOf(recipe.getTime() % 60) + "m");
//        stars.setText(String.valueOf(recipe.getStars()) + " / 5");
    }

    public void handleOpenRecipeAction() throws Exception {
        org.ckbk.sre.Main.primaryStage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Recipe.fxml"));
        primaryStage.setTitle("RECIPE");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
        org.ckbk.sre.Main.primaryStage = primaryStage;
    }
}