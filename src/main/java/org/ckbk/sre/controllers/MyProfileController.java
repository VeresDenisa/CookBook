package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import org.ckbk.sre.Main;
import org.ckbk.sre.model.Recipe;
import org.ckbk.sre.services.RecipeListService;
import org.ckbk.sre.services.RecipeService;

import java.io.IOException;
import java.util.ArrayList;

public class MyProfileController {
    @FXML
    private Pane pane;
    @FXML
    private Button prev;
    @FXML
    private Button next;

    private int nrPage;
    private int current;

    @FXML
    public void initialize(){
        nrPage = 1;
        current = 1;
        reloadPages();
    }

    public void reloadPages(){
        while (pane.getChildren().size() != 0) {
            pane.getChildren().remove(0);
        }
        if(current == 1) {
            ArrayList<Integer> mine = RecipeService.getMyRecipes();

            if(!(mine.isEmpty())) {
                for (int i = 0; i < 3; i++) {
                    Recipe recipe = RecipeService.getRecipe(mine.get((nrPage - 1)) * 3 + i);
                    if (recipe == null) continue;

                    FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource("RecipeBox.fxml"));

                    try {
                        Pane p = loader.load();
                        ((RecipeBoxController) (loader.getController())).load((nrPage - 1) * 3 + i);
                        pane.getChildren().add(p);
                        p.setLayoutX(70);
                        p.setLayoutY(20 + i * 110);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                next.setVisible(true);
                prev.setVisible(true);

                if (nrPage == 1) {
                    prev.setVisible(false);
                }
                if (nrPage == mine.size() / 3 + 1) {
                    next.setVisible(false);
                }
            }
        }else if(current == 2){
            ArrayList<Integer> fav = RecipeListService.getMyFavRecipes();
            System.out.println(fav.size());
            if(!(fav.isEmpty())) {
                for (int i = 0; i < 3; i++) {
                    Recipe recipe = RecipeService.getRecipe(fav.get((nrPage - 1)) * 3 + i);
                    if (recipe == null) continue;

                    FXMLLoader loader = new FXMLLoader(Main.class.getClassLoader().getResource("RecipeBox.fxml"));

                    try {
                        Pane p = loader.load();
                        ((RecipeBoxController) (loader.getController())).load((nrPage - 1) * 3 + i);
                        pane.getChildren().add(p);
                        p.setLayoutX(70);
                        p.setLayoutY(20 + i * 110);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                next.setVisible(true);
                prev.setVisible(true);

                if (nrPage == 1) {
                    prev.setVisible(false);
                }
                if (nrPage == fav.size() / 3 + 1) {
                    next.setVisible(false);
                }
            }
        }
    }

    @FXML
    public void handleGoNext() throws Exception{
        nrPage++;
        reloadPages();
    }

    @FXML
    public void handleGoPrev() throws Exception{
        nrPage--;
        reloadPages();
    }

    public void handleGoMine() {
        nrPage = 1;
        current = 1;
        reloadPages();
    }
    public void handleGoFav() {
        nrPage = 1;
        current = 2;
        reloadPages();
    }
    public void handleGoToDo() {
        nrPage = 1;
        current = 3;
        reloadPages();
    }
    public void handleGoDone() {
        nrPage = 1;
        current = 4;
        reloadPages();
    }
    public void handleGoNow() {
    }
}
