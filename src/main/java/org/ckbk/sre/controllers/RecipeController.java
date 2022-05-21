package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.ckbk.sre.model.Recipe;
import org.ckbk.sre.model.User;
import org.ckbk.sre.services.RecipeListService;
import org.ckbk.sre.services.RecipeService;
import org.ckbk.sre.services.UserService;

public class RecipeController {
    @FXML
    private Text title;
    @FXML
    private Text author;
    @FXML
    private Text type;
    @FXML
    private ImageView complexity;
    @FXML
    private Text time;
    @FXML
    private Text description;
    @FXML
    private Text addMessage;
    @FXML
    private ImageView image;

    @FXML
    private Button fav;
    @FXML
    private Button toDo;
    @FXML
    private Button now;
    @FXML
    private Button done;

    private static int index;
    public static void setIndex(int i) { index = i; }

    @FXML
    public void initialize() {
        Recipe recipe = RecipeService.getRecipeRepository().find().toList().get(index);
        title.setText(recipe.getName());
        author.setText(recipe.getAuthor());
        image.setImage(new Image(recipe.getImage()));
        if(recipe.getType() == Recipe.TYPE.Breakfast) type.setText("Breakfast");
        else if(recipe.getType() == Recipe.TYPE.Lunch) type.setText("Lunch");
        else if(recipe.getType() == Recipe.TYPE.Dinner) type.setText("Dinner");
        else type.setText("Other");
        switch (recipe.getComplexity()){
            case 0:
                complexity.setImage(new Image("images/icon/icon_stars_0.png"));
                break;
            case 1:
                complexity.setImage(new Image("images/icon/icon_stars_1.png"));
                break;
            case 2:
                complexity.setImage(new Image("images/icon/icon_stars_2.png"));
                break;
            case 3:
                complexity.setImage(new Image("images/icon/icon_stars_3.png"));
                break;
            case 4:
                complexity.setImage(new Image("images/icon/icon_stars_4.png"));
                break;
            default:
                complexity.setImage(new Image("images/icon/icon_stars_5.png"));
                break;
        }
        time.setText(recipe.getTime() / 60 + "h " + recipe.getTime() % 60 + "m");
        description.setText(recipe.getDescription());

        if(UserService.getUser().getRole() == User.ROLE.Client){
            fav.setVisible(true);
            toDo.setVisible(true);
            now.setVisible(true);
            done.setVisible(true);
        }else{
            fav.setVisible(false);
            toDo.setVisible(false);
            now.setVisible(false);
            done.setVisible(false);
        }
    }

    @FXML
    public void handleAddToFavAction() {
        RecipeListService.addRecipeList(UserService.getUser().getUsername(), index, false, false, false, true);
        addMessage.setText("Recipe added to My Favorites Recipes!");
    }

    @FXML
    public void handleAddToToDoAction() {
        RecipeListService.addRecipeList(UserService.getUser().getUsername(), index, true, false, false, false);
        addMessage.setText("Recipe added to My To Do Recipes!");
    }

    @FXML
    public void handleAddToNowAction() {
        RecipeListService.addRecipeList(UserService.getUser().getUsername(), index, false, true, false, false);
        addMessage.setText("Recipe added to My Doing Now Recipes!");
    }

    @FXML
    public void handleAddToDoneAction() {
        RecipeListService.addRecipeList(UserService.getUser().getUsername(), index, false, false, true, false);
        addMessage.setText("Recipe added to My Done Recipes!");
    }
}
