package pl.edu.agh.userInterface.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import pl.edu.agh.actions.Action;
import pl.edu.agh.actions.ActionResult;
import pl.edu.agh.actions.implementations.LoginAction;
import pl.edu.agh.userInterface.gui.GraphicsUserInterface;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    private GraphicsUserInterface gui;
    private ActionResult result;
    private String name = "Login Page";

    @FXML
    private AnchorPane loginPageAnchorPane;

    @FXML
    private ImageView avatarImageView;

    @FXML
    private TextField login;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private CheckBox showPasswordCheckBox;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public LoginPageController(GraphicsUserInterface gui) {

        this.gui = gui;
    }

    private void doSomethingWithResults(ActionResult result) {
        if (result.getActionStatus().equals("OK"))
            gui.changeSceneTo("homePage");
        else {
            gui.showError("Incorrect login or password");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void showPassword(ActionEvent event) {
        if (showPasswordCheckBox.isSelected()) {
            passwordTextField.setText(passwordField.getText());
            passwordTextField.setVisible(true);
            passwordField.setVisible(false);
        } else {
            passwordField.setText(passwordTextField.getText());
            passwordTextField.setVisible(false);
            passwordField.setVisible(true);
        }
    }

    @FXML
    private void submit() {
        if (showPasswordCheckBox.isSelected())
            passwordField.setText(passwordTextField.getText());
        Action loginAction = new LoginAction();
        loginAction.setParameter("login", login.getText());
        loginAction.setParameter("password", passwordField.getText());

        gui.addActionToQueue(loginAction);
        result = gui.getActionResultFromQueue();
        while (result == null) {
            result = gui.getActionResultFromQueue();
        }
        doSomethingWithResults(result);
    }

    @FXML
    private void signIn() {
        gui.changeSceneTo("registrationPage");
    }
}
