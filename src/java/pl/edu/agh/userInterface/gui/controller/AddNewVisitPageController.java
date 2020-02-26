package pl.edu.agh.userInterface.gui.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import pl.edu.agh.actions.Action;
import pl.edu.agh.actions.ActionResult;
import pl.edu.agh.actions.implementations.ListServiceAction;
import pl.edu.agh.actions.implementations.ListUsersAction;
import pl.edu.agh.actions.implementations.ReserveVisitAction;
import pl.edu.agh.appContext.AppContext;
import pl.edu.agh.databaseModel.User;
import pl.edu.agh.objects.services.Services;
import pl.edu.agh.userInterface.gui.GraphicsUserInterface;

import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

public class AddNewVisitPageController implements Initializable{

    private GraphicsUserInterface gui;
    private ActionResult result;
    private ShowMyVisitsPageController visit;
    private ListUsersPageController controller;
    private final DatePicker calendarDatePicker = new DatePicker();
    private ObservableList usersList = FXCollections.observableArrayList();
    private ObservableList servicesList = FXCollections.observableArrayList();
    private boolean first = true;
    private String name = "Add New Visit Page";

    @FXML
    private ComboBox<User> chooseEmployeeComboBox;

    @FXML
    private ComboBox<User> chooseClientComboBox;

    @FXML
    private ComboBox<Services> chooseServiceComboBox;

    @FXML
    private CheckBox reservedCheckBox;

    @FXML
    private CheckBox confirmedCheckBox;

    @FXML
    private Button returnToHomePageButton;

    @FXML
    private AnchorPane pane;

    @FXML
    private TextField pickStartTime;

    @FXML
    private TextField pickEndTime;

    @FXML
    private Button addVisitButton;

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private JFXTimePicker timePicker;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public AddNewVisitPageController(GraphicsUserInterface gui) {
        this.gui = gui;
    }

    private void viewEmployee() {
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
            chooseEmployeeComboBox.setItems(usersList);
        }
    }

    private void viewClient() {
        ListUsersAction listUsersAction = new ListUsersAction();
        listUsersAction.setParameter("role", "CLIENT");
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
            chooseClientComboBox.setItems(usersList);
        }
    }

    private void viewService() {
        ListServiceAction listServiceAction = new ListServiceAction();
        gui.addActionToQueue(listServiceAction);
        result = gui.getActionResultFromQueue();
        while (result == null) {
            result = gui.getActionResultFromQueue();
        }
        if (result.getActionStatus().equals("OK")) {
            List<Services> returnedServicesList = (List<Services>) result.getReturnObject();
            if (returnedServicesList != null && !returnedServicesList.isEmpty()) {
                servicesList = FXCollections.observableArrayList(returnedServicesList);
            }
            chooseServiceComboBox.setItems(servicesList);
        }
    }

    private void stringConverter() {
        chooseClientComboBox.setConverter(new StringConverter<User>() {

            @Override
            public String toString(User user) {
                return user.getFirstName() + " " + user.getLastName();
            }

            @Override
            public User fromString(String string) {
                return chooseClientComboBox.getItems().stream().filter(u ->
                        (u.getFirstName() + " " + u.getLastName()).equals(string)).findFirst().orElse(null);
            }
        });

        chooseEmployeeComboBox.setConverter(chooseClientComboBox.getConverter());

        chooseServiceComboBox.setConverter(new StringConverter<Services>() {
            @Override
            public String toString(Services services) {
                return services.getName();
            }

            @Override
            public Services fromString(String string) {
                return chooseServiceComboBox.getItems().stream().filter(s ->
                        s.getName().equals(string)).findFirst().orElse(null);
            }
        });
    }

    private void chooseDate() {
        confirmedCheckBox.setVisible(AppContext.getLoggedUser().getPermissions().contains("confirmVisit"));
        if (chooseClientComboBox.getItems().contains(AppContext.getLoggedUser()))
            chooseClientComboBox.getSelectionModel().select(AppContext.getLoggedUser());
        if (chooseEmployeeComboBox.getItems().contains(AppContext.getLoggedUser()))
            chooseEmployeeComboBox.getSelectionModel().select(AppContext.getLoggedUser());

        LocalDateTime ldt = LocalDateTime.now();
        timePicker.set24HourView(true);
        timePicker.setValue(ldt.toLocalTime());
        datePicker.setValue(ldt.toLocalDate());

        setTime();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewEmployee();
        viewClient();
        viewService();
        stringConverter();
        chooseDate();
    }

    @FXML
    void addVisit(ActionEvent event) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime startDate = LocalDateTime.parse(pickStartTime.getText(), formatter);
            LocalDateTime endDate = startDate.plusMinutes(chooseServiceComboBox.getSelectionModel().getSelectedItem().getMinutes());

            Action reserveNewVisitAction = new ReserveVisitAction();
            reserveNewVisitAction.setParameter("usersByEmployee", chooseEmployeeComboBox.getValue());
            reserveNewVisitAction.setParameter("usersByCreator", chooseClientComboBox.getValue());
            reserveNewVisitAction.setParameter("reserved", reservedCheckBox.isSelected());
            reserveNewVisitAction.setParameter("confirmed", confirmedCheckBox.isSelected());
            reserveNewVisitAction.setParameter("startTime", Timestamp.valueOf(startDate));
            reserveNewVisitAction.setParameter("endTime", Timestamp.valueOf(endDate));
            reserveNewVisitAction.setParameter("service", chooseServiceComboBox.getSelectionModel().getSelectedItem().getName());
            gui.addActionToQueue(reserveNewVisitAction);
            result = gui.getActionResultFromQueue();
            while (result == null) {
                result = gui.getActionResultFromQueue();
            }
            if (result.getActionStatus().equals("ERROR")) {
                gui.showError(result.getErrorMessage());
            }
        } catch (NullPointerException e) {
            gui.showError("SOMETHING WENT WRONG. \n MAKE SURE THAT YOU HAVE FILLED IN EVERY FIELD");
        }
    }

    @FXML
    void reserved(ActionEvent event) {
        chooseClientComboBox.setVisible(reservedCheckBox.isSelected());
        chooseClientComboBox.getSelectionModel().select(-1);
        confirmedCheckBox.setVisible(reservedCheckBox.isSelected());
        confirmedCheckBox.setSelected(false);
    }

    @FXML
    void returnToHomePage(ActionEvent event) {
        gui.changeSceneTo("homePage");
    }

    @FXML
    public void setTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        pickStartTime.setText(datePicker.getValue().toString() + " " + formatter.format(timePicker.getValue()).toString());
    }


}
