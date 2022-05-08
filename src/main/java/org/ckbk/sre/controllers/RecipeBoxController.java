package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RecipeBoxController implements Initializable {
    public Text type;
    @FXML
    private Text name;
    @FXML
    private Text author;
    @FXML
    private Text complexity;
    @FXML
    private Text time;
    @FXML
    private Text stars;
    @FXML
    private Text image;
    @FXML
    private Text description;

    @FXML
    public void showCompressedRecipe() {
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
