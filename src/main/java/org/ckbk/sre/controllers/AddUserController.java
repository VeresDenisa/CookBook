package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.ckbk.sre.exceptions.EmptyInputFieldException;
import org.ckbk.sre.exceptions.UsernameAlreadyExistsException;
import org.ckbk.sre.services.UserService;

public class AddUserController {
    @FXML
    private ChoiceBox roleField;
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
    public void handleRegisterAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), mailField.getText(), nrTelField.getText(), lastNameField.getText(), firstNameField.getText(), String.valueOf(roleField.getValue()));
            registrationMessage.setText(roleField.getValue() + " account created successfully!");
        } catch (EmptyInputFieldException | UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        }
    }
}
