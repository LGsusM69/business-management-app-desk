package com.polibius.businessmanagementappdesk.controller;

import com.polibius.businessmanagementappdesk.api.AuthService;
import com.polibius.businessmanagementappdesk.repository.UserRepository;
import com.polibius.businessmanagementappdesk.util.PasswordUtils;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private void initialize() {
        System.out.println("Login controller initialized");
    }

    @FXML
    protected void onLoginButtonClick() {
        String email = emailField.getText();
        String inputPassword = passwordField.getText();
        System.out.println("Email: " + email);
        System.out.println("Password: " + inputPassword);
        boolean credentialsAreValid = validateInput(email, inputPassword);
        if(!credentialsAreValid) return;

        boolean onlineSuccess = AuthService.loginOnline(email, inputPassword);
        if(onlineSuccess) {
            System.out.println("Online login success!");

        }

        String inputPasswordHash = PasswordUtils.hashPassword(inputPassword);
        String storedHash = UserRepository.getPasswordHashByEmail(email);

        System.out.println("input: " + inputPasswordHash);
        System.out.println("stored: " + storedHash);

        if(storedHash != null && storedHash.equals(inputPasswordHash)) {
            System.out.println("Login success!");
        } else {
            System.out.println("Login failed!");
        }

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
