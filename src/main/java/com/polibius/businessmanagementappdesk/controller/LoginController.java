package com.polibius.businessmanagementappdesk.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private void initialize() {
        System.out.println("Login controller initialized");
    }

    @FXML
    protected void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        boolean credentialsAreValid = validateInput(username, password);
        if(!credentialsAreValid) return;
    }

    private boolean validateInput(String username, String password) {
        if(
            username == ""
            || !username.contains("@")
            || !username.contains(".com")
            || username.contains(" ")
        ) {
            System.out.println("invalid username");
            return false;
        }
        if(password == "") {
            System.out.println("password cant be empty");
            return false;
        }
        return true;
    }
}
