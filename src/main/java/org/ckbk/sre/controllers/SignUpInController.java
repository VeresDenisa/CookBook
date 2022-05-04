package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.ckbk.sre.exceptions.EmptyInputFieldException;
import org.ckbk.sre.exceptions.UsernameAlreadyExistsException;
import org.ckbk.sre.model.User;
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
    public void handleRegisterAction() {
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), mailField.getText(), nrTelField.getText(), lastNameField.getText(), firstNameField.getText(), User.ROLE.Client);
            registrationMessage.setText("Client account created successfully!");
        } catch (EmptyInputFieldException e) {
            registrationMessage.setText(e.getMessage());
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        }
    }
}
