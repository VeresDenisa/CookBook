package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.ckbk.sre.services.UserService;

import java.net.URL;
import java.util.ResourceBundle;

public class SidebarController {
    @FXML
    public ImageView profileImage;

    @FXML
    public void handleLogOutAction() throws Exception {
        org.ckbk.sre.Main.primaryStage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("SignUpIn.fxml"));
        primaryStage.setTitle("SIGN IN / SIGN UP");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
        org.ckbk.sre.Main.primaryStage = primaryStage;
    }

    @FXML
    public void handleGoMyProfile() throws Exception{
        org.ckbk.sre.Main.primaryStage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MyProfile.fxml"));
        primaryStage.setTitle("MY PROFILE");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
        org.ckbk.sre.Main.primaryStage = primaryStage;
    }

    @FXML
    public void handleGoAddRecipe() throws Exception{
        org.ckbk.sre.Main.primaryStage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AddRecipe.fxml"));
        primaryStage.setTitle("ADD RECIPE");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
        org.ckbk.sre.Main.primaryStage = primaryStage;
    }

    @FXML
    public void handleGoRecipeBook() throws Exception{
        org.ckbk.sre.Main.primaryStage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("RecipeBook.fxml"));
        primaryStage.setTitle("RECIPES BOOK");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
        org.ckbk.sre.Main.primaryStage = primaryStage;
    }

//    @Override
//    public void initialize(URL url, ResourceBundle resourceBundle) {
//        Image image;
//        if(UserService.getUser().getImage().isEmpty()) image = new Image("@../images/profile/row-1-column-1.png");
//        else image = new Image(UserService.getUser().getImage());
//        profileImage.setImage(image);
//    }
}
