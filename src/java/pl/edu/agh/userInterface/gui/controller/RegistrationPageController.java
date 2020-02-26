package pl.edu.agh.userInterface.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pl.edu.agh.actions.Action;
import pl.edu.agh.actions.ActionResult;
import pl.edu.agh.actions.implementations.RegistrationAction;
import pl.edu.agh.userInterface.gui.GraphicsUserInterface;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationPageController implements Initializable {

    private GraphicsUserInterface gui;
    private ActionResult result;
    private String name = "Registration Page";

    @FXML
    private TextField login;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField emailAddress;

    @FXML
    private TextField phoneNumber;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private CheckBox showPasswordCheckBox;

    @FXML
    private Button createAccountButton;

    @FXML
    private Button returnToLoginPageButton;

    public RegistrationPageController(GraphicsUserInterface gui) {
        this.gui = gui;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    private boolean validateEmail() {
        Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
        Matcher m = p.matcher(emailAddress.getText());
        if (m.find() && m.group().equals(emailAddress.getText())) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Incorrect E-Mail Address");
            alert.setHeaderText(null);
            alert.setContentText("Please, enter the correct e-mail address");
            alert.showAndWait();
            return false;
        }
    }

    private void doSomethingWithResults(ActionResult result) {
        if (result.getActionStatus().equals("OK"))
            gui.changeSceneTo("loginPage");
        else {
            gui.showError("Something gone wrong!");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    void returnToLoginPage(ActionEvent event) {
        gui.changeSceneTo("loginPage");
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
    void submit(ActionEvent event) {
        if (showPasswordCheckBox.isSelected())
            passwordField.setText(passwordField.getText());
        if (validateEmail() == true) {
            Action registrationAction = new RegistrationAction();
            registrationAction.setParameter("login", login.getText());
            registrationAction.setParameter("password", passwordField.getText());
            registrationAction.setParameter("firstName", firstName.getText());
            registrationAction.setParameter("lastName", lastName.getText());
            registrationAction.setParameter("phoneNumber", phoneNumber.getText());
            registrationAction.setParameter("email", emailAddress.getText());
            registrationAction.setParameter("role", "CLIENT");
            gui.addActionToQueue(registrationAction);
            result = gui.getActionResultFromQueue();
            while (result == null) {
                result = gui.getActionResultFromQueue();
            }
            doSomethingWithResults(result);
        }
    }
}
