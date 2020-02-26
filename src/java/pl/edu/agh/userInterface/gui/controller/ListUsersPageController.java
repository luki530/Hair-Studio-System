package pl.edu.agh.userInterface.gui.controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import pl.edu.agh.actions.Action;
import pl.edu.agh.actions.ActionResult;
import pl.edu.agh.actions.implementations.DeleteUserAction;
import pl.edu.agh.actions.implementations.ListUsersAction;
import pl.edu.agh.actions.implementations.RegistrationAction;
import pl.edu.agh.databaseModel.User;
import pl.edu.agh.userInterface.gui.GraphicsUserInterface;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListUsersPageController implements Initializable {

    private GraphicsUserInterface gui;
    private ActionResult result;
    private String name = "List Users Page";

    @FXML
    private Button removeButton;

    @FXML
    private Button addNewEmployeeButton;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private TextField passwordTextField;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField emailAddress;

    @FXML
    private TextField phoneNumber;

    @FXML
    private Button returnToHomePageButton;

    @FXML
    private TextField filterField;

    @FXML
    private TableView<User> usersTable;

    @FXML
    private TableColumn<User, String> userLogin = new TableColumn<>();

    @FXML
    private TableColumn<User, String> userFirstName = new TableColumn<>();

    @FXML
    private TableColumn<User, String> userLastName = new TableColumn<>();

    @FXML
    private TableColumn<User, String> userRole = new TableColumn<>();

    @FXML
    private TableColumn<User, String> userPhoneNumber = new TableColumn<>();

    @FXML
    private TableColumn<User, String> userEmail = new TableColumn<>();

    @FXML
    private CheckBox showPasswordCheckBox;

    public ListUsersPageController(GraphicsUserInterface gui) {
        this.gui = gui;
    }

    private ObservableList usersList = FXCollections.observableArrayList();

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

    private void viewUsers() {
        ListUsersAction listUsersAction = new ListUsersAction();
        gui.addActionToQueue(listUsersAction);
        result = gui.getActionResultFromQueue();
        while (result == null) {
            result = gui.getActionResultFromQueue();
        }
        if (result.getActionStatus().equals("OK")) {

            List<User> returnedUsersList = (List<User>) result.getReturnObject();
            if (returnedUsersList != null && !returnedUsersList.isEmpty()) {
                usersList = FXCollections.observableArrayList(returnedUsersList);
            }
            userLogin.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getLogin()));
            userFirstName.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFirstName()));
            userLastName.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getLastName()));
            userRole.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getRole()));
            userPhoneNumber.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPhoneNumber()));
            userEmail.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().geteMail()));

            FilteredList<User> filteredData = new FilteredList<>(usersList, p -> true);

            filterField.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(user -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();

                    if (user.getLogin().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (user.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (user.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (user.getRole().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (user.getPhoneNumber().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (user.geteMail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<User> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(usersTable.comparatorProperty());
            usersTable.setItems(sortedData);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewUsers();
    }

    private void doSomethingWithResults(ActionResult result) {
        if (result.getActionStatus().equals("OK")) {
            viewUsers();
            login.clear();
            password.clear();
            lastName.clear();
            firstName.clear();
            emailAddress.clear();
            phoneNumber.clear();
            result = null;
        } else {
            gui.showError("Something gone wrong!");
        }
    }

    ;

    @FXML
    void showPassword(ActionEvent event) {
        if (showPasswordCheckBox.isSelected()) {
            passwordTextField.setText(password.getText());
            passwordTextField.setVisible(true);
            password.setVisible(false);
        } else {
            password.setText(passwordTextField.getText());
            passwordTextField.setVisible(false);
            password.setVisible(true);
        }
    }

    @FXML
    void addNewEmployee(ActionEvent event) {
        if (showPasswordCheckBox.isSelected())
            password.setText(passwordTextField.getText());
        if (validateEmail() == true) {
            Action addEmployeeAction = new RegistrationAction();
            addEmployeeAction.setParameter("login", login.getText());
            addEmployeeAction.setParameter("password", password.getText());
            addEmployeeAction.setParameter("firstName", firstName.getText());
            addEmployeeAction.setParameter("lastName", lastName.getText());
            addEmployeeAction.setParameter("phoneNumber", phoneNumber.getText());
            addEmployeeAction.setParameter("email", emailAddress.getText());
            addEmployeeAction.setParameter("role", "EMPLOYEE");
            gui.addActionToQueue(addEmployeeAction);
            result = gui.getActionResultFromQueue();
            while (result == null) {
                result = gui.getActionResultFromQueue();
            }
            doSomethingWithResults(result);
        }
    }

    @FXML
    void remove(ActionEvent event) {
        DeleteUserAction deleteUserAction = new DeleteUserAction();
        deleteUserAction.setParameter("user", usersTable.getSelectionModel().getSelectedItem());
        gui.addActionToQueue(deleteUserAction);
        result = gui.getActionResultFromQueue();
        while (result == null) {
            result = gui.getActionResultFromQueue();
        }
        doSomethingWithResults(result);
    }

    @FXML
    void returnToHomePage(ActionEvent event) {
        gui.changeSceneTo("homePage");
    }
}
