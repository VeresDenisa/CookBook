package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.ckbk.sre.model.Recipe;
import org.ckbk.sre.services.RecipeService;

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
    private ImageView image;
    @FXML
    private Button button;

    @FXML
    public void initialize() {
        if(RecipeService.getRecipeRepositorySize() >= 1) {
            Recipe recipe = RecipeService.getRecipe(1);
            title.setText(recipe.getName());
            image.setImage(new Image(recipe.getImage()));
            author.setText(recipe.getAuthor());
            if (recipe.getType() == Recipe.TYPE.Breakfast) type.setText("Breakfast");
            else if (recipe.getType() == Recipe.TYPE.Lunch) type.setText("Lunch");
            else if (recipe.getType() == Recipe.TYPE.Dinner) type.setText("Dinner");
            else type.setText("Other");
            complexity.setText(recipe.getComplexity() + " / 5");
            time.setText(recipe.getTime() / 60 + "h " + recipe.getTime() % 60 + "m");
            stars.setText(recipe.getStars() + " / 5");
        }
        else{
            button.setVisible(false);
        }
    }

    @FXML
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