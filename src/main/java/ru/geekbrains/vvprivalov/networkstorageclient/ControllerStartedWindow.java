package ru.geekbrains.vvprivalov.networkstorageclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ControllerStartedWindow implements Initializable {
    UseDBforCheckLoginPassword useDBforCheckLoginPassword;

    @FXML
    private Button btnSignIn;

    @FXML
    private Button btnSignUp;

    @FXML
    private TextField firstNameSingUp;

    @FXML
    private TextField lastNameSignUp;

    @FXML
    private TextField loginSignIn;

    @FXML
    private TextField loginSignUp;

    @FXML
    private PasswordField passwordSignUp;

    @FXML
    private PasswordField passwordSignIn;

    @FXML
    private Label labelInfo;

    // Иннициализация клиента
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        useDBforCheckLoginPassword = new UseDBforCheckLoginPassword();
    }

    // Обработка нажатия кнопки
    @FXML
    void onSignInButtonClick(ActionEvent event) {
        String login = loginSignIn.getText();
        String password = passwordSignIn.getText();

        boolean checkLoginPassword = useDBforCheckLoginPassword.checkLoginAndPasswordAtIdentification(login, password);

        if (checkLoginPassword) {
            labelInfo.setText("Пользователь с логином " + login + " найден. Идет загрузка вашего хранилища...");
            labelInfo.setVisible(true);
            useDBforCheckLoginPassword.closeDB();
        } else {
            labelInfo.setText("Неверный логин " + login + " или его пароль");
            labelInfo.setVisible(true);
            loginSignIn.requestFocus();
        }
        loginSignIn.clear();
        passwordSignIn.clear();
    }

    @FXML
    void onSignUpButtonClick(ActionEvent event) {
        String firstName = firstNameSingUp.getText();
        String lastName = lastNameSignUp.getText();
        String login = loginSignUp.getText();
        String password = passwordSignUp.getText();

        if(firstName.isEmpty() | lastName.isEmpty() | login.isEmpty() | password.isEmpty()) {
            labelInfo.setText("При регистрации все поля должны быть заполнены");
            labelInfo.setVisible(true);
            firstNameSingUp.requestFocus();
            return;
        }

        boolean checkAtRegistered = useDBforCheckLoginPassword.newUserRegistration(login, password, firstName, lastName);

        if (checkAtRegistered) {
            labelInfo.setText("Пользователь с логином " + login + " зарегистрирован");
            labelInfo.setVisible(true);
        } else {
            labelInfo.setText("Пользователь с логином " + login + " уже существует");
            labelInfo.setVisible(true);
            useDBforCheckLoginPassword.closeDB();

        }
        loginSignUp.clear();
        passwordSignUp.clear();
        firstNameSingUp.clear();
        lastNameSignUp.clear();
    }
}
