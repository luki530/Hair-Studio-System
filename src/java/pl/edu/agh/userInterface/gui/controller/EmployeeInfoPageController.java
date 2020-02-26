package pl.edu.agh.userInterface.gui.controller;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import pl.edu.agh.actions.ActionResult;
import pl.edu.agh.actions.implementations.ListUsersAction;
import pl.edu.agh.appContext.AppContext;
import pl.edu.agh.databaseModel.User;
import pl.edu.agh.userInterface.gui.GraphicsUserInterface;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class EmployeeInfoPageController implements Initializable {

    private GraphicsUserInterface gui;
    private ActionResult result;
    private ObservableList usersList = FXCollections.observableArrayList();
    private String name = "Employee Info Page";

    @FXML
    private TextField filterEmployee;

    @FXML
    private Button returnToHomePageButton;

    @FXML
    private TableView<User> employeeInformationTableView;

    @FXML
    private TableColumn<User, String> employeeFirstNameTableColumn;

    @FXML
    private TableColumn<User, String> employeeLastNameTableColumn;

    @FXML
    private TableColumn<User, String> employeePhoneNumberTableColumn;

    @FXML
    private TableColumn<User, String> employeeEmailAddressTableColumn;

    public EmployeeInfoPageController(GraphicsUserInterface gui) {
        this.gui = gui;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    private void viewEmployees() {
        ListUsersAction listUsersAction = new ListUsersAction();
        listUsersAction.setParameter("role", "EMPLOYEE");
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
            employeeFirstNameTableColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFirstName()));
            employeeLastNameTableColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getLastName()));
            employeePhoneNumberTableColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPhoneNumber()));
            employeeEmailAddressTableColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().geteMail()));


            FilteredList<User> filteredData = new FilteredList<>(usersList, p -> true);

            filterEmployee.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(user -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if (user.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (user.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
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

            sortedData.comparatorProperty().bind(employeeInformationTableView.comparatorProperty());
            employeeInformationTableView.setItems(sortedData);
        }
        employeeInformationTableView.getSelectionModel().select(AppContext.getCurrentEmployee());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewEmployees();
    }

    @FXML
    void returnToHomePage(ActionEvent event) {
        gui.changeSceneTo("homePage");
    }
}