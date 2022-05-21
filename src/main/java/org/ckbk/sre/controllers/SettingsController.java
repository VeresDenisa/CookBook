package org.ckbk.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.ckbk.sre.exceptions.*;
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
        reload();
    }

    public void reload(){
        usernameField.setText(UserService.getUser().getUsername());
        roleField.setText(String.valueOf(UserService.getUser().getRole()));
        firstNamePrevField.setText(UserService.getUser().getFirstName());
        lastNamePrevField.setText(UserService.getUser().getLastName());
        nrTelPrevField.setText(UserService.getUser().getNrTel());
        mailPrevField.setText(UserService.getUser().getMail());
        lastNameField.setText("");
        firstNameField.setText("");
        nrTelField.setText("");
        mailField.setText("");
        passwordFieldConfirm.setText("");
        passwordFieldEditConfirm.setText("");
        passwordFieldEdit.setText("");
        confirmationMessage.setText("");
    }

    @FXML
    public void handleEditAction() {
        try{
            UserService.editUser(passwordFieldConfirm.getText(), lastNameField.getText(), firstNameField.getText(), mailField.getText(), nrTelField.getText(), passwordFieldEdit.getText(), passwordFieldEditConfirm.getText());
            reload();
        }catch (EmailAddressIsNotValidException | InvalidCredentialsException | NewPasswordIsNotConfirmedException | PhoneNumberIsNotValidException | PasswordComplexityIsTooLowException e){
            confirmationMessage.setText(e.getMessage());
        }
    }
}
