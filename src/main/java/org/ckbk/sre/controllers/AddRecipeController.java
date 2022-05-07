package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.ckbk.sre.exceptions.EmptyInputFieldException;
import org.ckbk.sre.exceptions.UsernameAlreadyExistsException;
import org.ckbk.sre.model.Recipe;
import org.ckbk.sre.model.User;
import org.ckbk.sre.services.RecipeService;
import org.ckbk.sre.services.UserService;

public class AddRecipeController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField complexityField;
    @FXML
    private TextField timeField;
    @FXML
    private ChoiceBox typeField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField imageField;

    @FXML
    public void handleAddRecipeAction() throws Exception{
        try {
            RecipeService.addRecipe(nameField.getText(), UserService.getUsername(), Integer.valueOf(complexityField.getText()), Integer.valueOf(timeField.getText()), String.valueOf(typeField.getValue()), imageField.getText(), descriptionField.getText());
                org.ckbk.sre.Main.primaryStage.close();
                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("RecipeBook.fxml"));
                primaryStage.setTitle("RECIPES BOOK");
                primaryStage.setScene(new Scene(root, 900, 500));
                primaryStage.setResizable(false);
                primaryStage.show();
                org.ckbk.sre.Main.primaryStage = primaryStage;
        } catch (EmptyInputFieldException e) {
        }
    }
}
