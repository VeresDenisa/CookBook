package org.ckbk.sre;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.ckbk.sre.services.FileSystemService;
import org.ckbk.sre.services.RecipeService;
import org.ckbk.sre.services.UserService;
import org.dizitart.no2.Nitrite;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.ckbk.sre.services.FileSystemService.getPathToFile;

public class Main extends Application {

    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        initDirectory();
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("UserFile.db").toFile())
                .openOrCreate("test", "test");
        UserService.initDatabase(database);
        RecipeService.initDatabase(database);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("SignUpIn.fxml"));
        primaryStage.setTitle("SIGN IN / SIGN UP");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
        this.primaryStage = primaryStage;
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
