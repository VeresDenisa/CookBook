package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.ckbk.sre.exceptions.*;
import org.ckbk.sre.services.UserService;

public class SignUpInController {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField mailField;
    @FXML
    private TextField nrTelField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private Text logInMessage;
    @FXML
    private PasswordField passwordLogInField;
    @FXML
    private TextField usernameLogInField;

    @FXML
    public void handleRegisterAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), mailField.getText(), nrTelField.getText(), lastNameField.getText(), firstNameField.getText(), "Client");
            registrationMessage.setText("Client account created successfully!");
        } catch (EmptyInputFieldException | UsernameAlreadyExistsException | PhoneNumberIsNotValidException | EmailAddressIsNotValidException e) {
            registrationMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleLogInAction() throws Exception {
        try {
            UserService.logInUser(usernameLogInField.getText(), passwordLogInField.getText());
            org.ckbk.sre.Main.primaryStage.close();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("RecipeBook.fxml"));
            primaryStage.setTitle("RECIPES BOOK");
            primaryStage.setScene(new Scene(root, 900, 500));
            primaryStage.setResizable(false);
            primaryStage.show();
            org.ckbk.sre.Main.primaryStage = primaryStage;
        } catch (EmptyInputFieldException | InvalidCredentialsException e) {
            logInMessage.setText(e.getMessage());
        }
    }
}
