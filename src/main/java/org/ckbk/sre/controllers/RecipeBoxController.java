package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.ckbk.sre.model.Recipe;
import org.ckbk.sre.services.RecipeService;

public class RecipeBoxController {
    @FXML
    private Text type;
    @FXML
    private Text title;
    @FXML
    private Text author;
    @FXML
    private ImageView complexity;
    @FXML
    private Text time;
    @FXML
    private ImageView image;

    private int index;

    @FXML
    public void load(int i) {
            Recipe recipe = RecipeService.getRecipe(i);
            this.index = i;
            title.setText(recipe.getName());
            image.setImage(new Image(recipe.getImage()));
            author.setText(recipe.getAuthor());
            if (recipe.getType() == Recipe.TYPE.Breakfast) type.setText("Breakfast");
            else if (recipe.getType() == Recipe.TYPE.Lunch) type.setText("Lunch");
            else if (recipe.getType() == Recipe.TYPE.Dinner) type.setText("Dinner");
            else type.setText("Other");
        if(recipe.getComplexity() == 0)
            complexity.setImage(new Image("images/icon/icon_stars_0.png"));
        else if(recipe.getComplexity() == 1)
            complexity.setImage(new Image("images/icon/icon_stars_1.png"));
        else if(recipe.getComplexity() == 2)
            complexity.setImage(new Image("images/icon/icon_stars_2.png"));
        else if(recipe.getComplexity() == 3)
            complexity.setImage(new Image("images/icon/icon_stars_3.png"));
        else if(recipe.getComplexity() == 4)
            complexity.setImage(new Image("images/icon/icon_stars_4.png"));
        else
            complexity.setImage(new Image("images/icon/icon_stars_5.png"));
        time.setText(recipe.getTime() / 60 + "h " + recipe.getTime() % 60 + "m");
    }

    @FXML
    public void handleOpenRecipeAction() throws Exception {
        RecipeController.setIndex(this.index);
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