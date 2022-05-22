package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.ckbk.sre.exceptions.EmptyInputFieldException;
import org.ckbk.sre.exceptions.InputNotANumberException;
import org.ckbk.sre.services.ProductService;
import org.ckbk.sre.services.UserService;

public class AddProductController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField quantityField;
    @FXML
    private TextField imageField;
    @FXML
    private Text errorMessage;

    @FXML
    public void handleAddProductAction() throws Exception{
        try {
            ProductService.addProduct(nameField.getText(), imageField.getText(), UserService.getUser().getUsername(), quantityField.getText(), priceField.getText());
            org.ckbk.sre.Main.primaryStage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("RecipeBook.fxml"));
            primaryStage.setTitle("RECIPES BOOK");
            primaryStage.setScene(new Scene(root, 900, 500));
            primaryStage.setResizable(false);
            primaryStage.show();
            org.ckbk.sre.Main.primaryStage = primaryStage;
        } catch (EmptyInputFieldException | InputNotANumberException e) {
            errorMessage.setText(e.getMessage());
        }
    }
}
