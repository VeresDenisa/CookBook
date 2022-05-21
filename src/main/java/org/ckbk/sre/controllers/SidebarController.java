package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.ckbk.sre.model.User;
import org.ckbk.sre.services.UserService;

public class SidebarController {
    @FXML
    private ImageView profileImage;

    @FXML
    private void initialize() {
        Image image = new Image(UserService.getUser().getImage());
        this.profileImage.setImage(image);
    }

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
        if(UserService.getUser().getRole() == User.ROLE.Client) {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MyProfile.fxml"));
            primaryStage.setScene(new Scene(root, 900, 500));
        } else if(UserService.getUser().getRole() == User.ROLE.Admin){
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MyAdminProfile.fxml"));
            primaryStage.setScene(new Scene(root, 900, 500));
        } else{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MyManagerProfile.fxml"));
            primaryStage.setScene(new Scene(root, 900, 500));
        }
        primaryStage.setTitle("MY PROFILE");
        primaryStage.setResizable(false);
        primaryStage.show();
        org.ckbk.sre.Main.primaryStage = primaryStage;
    }

    @FXML
    public void handleGoAddSomething() throws Exception{
        org.ckbk.sre.Main.primaryStage.close();
        Stage primaryStage = new Stage();
        if(UserService.getUser().getRole() == User.ROLE.Client) {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AddRecipe.fxml"));
            primaryStage.setTitle("ADD RECIPE");
            primaryStage.setScene(new Scene(root, 900, 500));
        } else if(UserService.getUser().getRole() == User.ROLE.Admin){
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AddUser.fxml"));
            primaryStage.setTitle("ADD USER");
            primaryStage.setScene(new Scene(root, 900, 500));
        } else{
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("AddProduct.fxml"));
            primaryStage.setTitle("ADD PRODUCT");
            primaryStage.setScene(new Scene(root, 900, 500));
        }
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

}