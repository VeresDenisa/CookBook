package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import org.ckbk.sre.Main;
import org.ckbk.sre.model.Recipe;
import org.ckbk.sre.services.RecipeService;

import java.io.IOException;

public class RecipeBookController {
    @FXML
    private Pane pane;
    @FXML
    private Button first;
    @FXML
    private Button prev;
    @FXML
    private Button next;
    @FXML
    private Button last;

    private int nrPage = 1;

    @FXML
    public void initialize(){
        reloadPages();
    }

    public void reloadPages(){
        while(pane.getChildren().size() != 0){
            pane.getChildren().remove(0);
        }
        for(int i = 0; i < 3; i++){
            Recipe recipe = RecipeService.getRecipe((nrPage - 1) * 3 + i);
            if(recipe == null) continue;

            FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource("RecipeBox.fxml"));

            try {
                Pane p = loader.load();
                ((RecipeBoxController)(loader.getController())).load((nrPage - 1) * 3 + i);
                pane.getChildren().add(p);
                p.setLayoutX(70);
                p.setLayoutY(20 + i * 120);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        first.setVisible(true);
        next.setVisible(true);
        prev.setVisible(true);
        last.setVisible(true);

        if(nrPage == 1){
            first.setVisible(false);
            prev.setVisible(false);
        }
        if(nrPage == (RecipeService.getRecipeRepositorySize() - 1) / 3 + 1){
            next.setVisible(false);
            last.setVisible(false);
        }
    }

    @FXML
    public void handleGoNext(){
        nrPage++;
        reloadPages();
    }

    @FXML
    public void handleGoPrev(){
        nrPage--;
        reloadPages();
    }

    @FXML
    public void handleGoFirst(){
        nrPage = 1;
        reloadPages();
    }

    @FXML
    public void handleGoLast(){
        nrPage = (int)((RecipeService.getRecipeRepositorySize() - 1) / 3 + 1);
        reloadPages();
    }
}
