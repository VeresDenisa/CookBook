package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.ckbk.sre.model.User;
import org.ckbk.sre.services.UserService;

import java.io.IOException;

public class UserBoxController {
    @FXML
    private ImageView image;
    @FXML
    private Text username;
    @FXML
    private Text email;

    private User user;

    public void load(User u){
        this.user = u;
        image.setImage(new Image(user.getImage()));
        username.setText(user.getUsername());
        email.setText(user.getMail());
    }

    @FXML
    public void handleDeleteAction() throws IOException {
        UserService.deleteClientAccount(user);
        reload();
    }

    @FXML
    public void handleCancelAction() throws IOException {
        UserService.cancelReportClient(user.getUsername());
        reload();
    }

    private void reload() throws IOException {
        org.ckbk.sre.Main.primaryStage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MyAdminProfile.fxml"));
        primaryStage.setTitle("MY PROFILE");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
        org.ckbk.sre.Main.primaryStage = primaryStage;
    }
}
