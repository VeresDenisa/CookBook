package org.ckbk.sre;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.ckbk.sre.services.FileSystemService;
import org.ckbk.sre.services.UserService;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("NUUUUUUU!!!! VREAU SA DOOOOOORM!!!!");
        initDirectory();
        System.out.println("NUUUUUUU!!!! VREAU SA DOOOOOORM!!!!");
        UserService.initDatabase();
        System.out.println("NUUUUUUU!!!! VREAU SA DOOOOOORM!!!!");
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("SignUpIn.fxml"));
        System.out.println("NUUUUUUU!!!! VREAU SA DOOOOOORM!!!!");
        primaryStage.setTitle("SIGN IN / SIGN UP");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void initDirectory() {
        Path applicationHomePath = FileSystemService.APPLICATION_HOME_PATH;
        if (!Files.exists(applicationHomePath))
            applicationHomePath.toFile().mkdirs();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
