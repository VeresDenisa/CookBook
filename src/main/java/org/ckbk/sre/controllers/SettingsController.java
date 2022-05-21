package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.ckbk.sre.services.UserService;

public class SettingsController {
    @FXML
    private Text roleField;
    @FXML
    private Text lastNamePrevField;
    @FXML
    private TextField lastNameField;
    @FXML
    private Text firstNamePrevField;
    @FXML
    private TextField firstNameField;
    @FXML
    private Text mailPrevField;
    @FXML
    private TextField mailField;
    @FXML
    private Text nrTelPrevField;
    @FXML
    private TextField nrTelField;
    @FXML
    private Text usernameField;
    @FXML
    private PasswordField passwordFieldEdit;
    @FXML
    private PasswordField passwordFieldEditConfirm;
    @FXML
    private PasswordField passwordFieldConfirm;
    @FXML
    private Text confirmationMessage;

    @FXML
    public void initialize(){
        usernameField.setText(UserService.getUser().getUsername());
        roleField.setText(String.valueOf(UserService.getUser().getRole()));
        firstNamePrevField.setText(UserService.getUser().getFirstName());
        lastNamePrevField.setText(UserService.getUser().getLastName());
        nrTelPrevField.setText(UserService.getUser().getNrTel());
        mailPrevField.setText(UserService.getUser().getMail());
    }

    @FXML
    public void handleEditAction() {
    }
}
