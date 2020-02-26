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
import pl.edu.agh.actions.implementations.ConfirmVisitAction;
import pl.edu.agh.actions.implementations.DeleteVisitAction;
import pl.edu.agh.actions.implementations.ListVisitsAction;
import pl.edu.agh.appContext.AppContext;
import pl.edu.agh.databaseModel.User;
import pl.edu.agh.databaseModel.Visit;
import pl.edu.agh.userInterface.gui.GraphicsUserInterface;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ShowMyVisitsPageController implements Initializable {

    private GraphicsUserInterface gui;
    private ActionResult result;
    private User loggedUser;
    private String name = "Show My Visits Page";

    @FXML
    private TableView<Visit> visits;

    @FXML
    private TableColumn<Visit, String> employeeVisitTableColumn;

    @FXML
    private TableColumn<Visit, String> statusOfVisitTableColumn;

    @FXML
    private TableColumn<Visit, String> startTimeOfVisitTableColumn;

    @FXML
    private TableColumn<Visit, String> endTimeOfVisitTableColumn;

    @FXML
    public TableColumn<Visit, String> typeOfServiceTableColumn;

    @FXML
    private TextField filterVisits;

    @FXML
    private Button deleteVisitButton;

    @FXML
    private Button confirmVisitButton;

    @FXML
    private Button returnToHomePageButton;

    public ShowMyVisitsPageController(GraphicsUserInterface gui) {
        this.gui = gui;
    }

    private ObservableList visitsList = FXCollections.observableArrayList();

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    private void viewVisits() {
        ListVisitsAction listVisitsAction = new ListVisitsAction();
        listVisitsAction.setParameter("currentUser", AppContext.getLoggedUser());
        gui.addActionToQueue(listVisitsAction);
        result = gui.getActionResultFromQueue();
        while (result == null) {
            result = gui.getActionResultFromQueue();
        }
        if (result.getActionStatus().equals("OK")) {

            List<User> returnedUsersList = (List<User>) result.getReturnObject();
            if (returnedUsersList != null && !returnedUsersList.isEmpty()) {
                visitsList = FXCollections.observableArrayList(returnedUsersList);
            }
            employeeVisitTableColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getUsersByEmployee().getFirstName() + " " + cellData.getValue().getUsersByEmployee().getLastName()));
            statusOfVisitTableColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getConfirmed() ? "CONFIRMED" : cellData.getValue().getBusy() ? "RESERVED" : "FREE"));
            startTimeOfVisitTableColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getStartTime().toString()));
            endTimeOfVisitTableColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getEndTime().toString()));
            typeOfServiceTableColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getService()));
            FilteredList<Visit> filteredData = new FilteredList<>(visitsList, p -> true);

            filterVisits.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(visit -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if ((visit.getUsersByEmployee().getFirstName() + " " + visit.getUsersByEmployee().getLastName()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if ((visit.getConfirmed() ? "CONFIRMED" : visit.getBusy() ? "RESERVED" : "FREE").toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (visit.getStartTime().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (visit.getEndTime().toString().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    } else if (visit.getService().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true;
                    }
                    return false;
                });
            });

            SortedList<Visit> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(visits.comparatorProperty());
            visits.setItems(sortedData);
        }
        result = null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        viewVisits();
        confirmVisitButton.setVisible(AppContext.getLoggedUser().getPermissions().contains("confirmVisit"));
        deleteVisitButton.setVisible(AppContext.getLoggedUser().getPermissions().contains("deleteVisit"));
    }

    @FXML
    void returnToHomePage(ActionEvent event) {
        gui.changeSceneTo("homePage");
    }

    @FXML
    void confirmVisit(ActionEvent event) {
        ConfirmVisitAction confirmVisitAction = new ConfirmVisitAction();
        confirmVisitAction.setParameter("visit", visits.getSelectionModel().getSelectedItem());
        gui.addActionToQueue(confirmVisitAction);
        result = gui.getActionResultFromQueue();
        while (result == null) {
            result = gui.getActionResultFromQueue();
        }
        if (result.getActionStatus().equals("OK"))
            viewVisits();
        visits.refresh();
        result = null;
    }

    @FXML
    void deleteVisit(ActionEvent event) {
        DeleteVisitAction addDeleteVisitAction = new DeleteVisitAction();
        addDeleteVisitAction.setParameter("visit", visits.getSelectionModel().getSelectedItem());
        gui.addActionToQueue(addDeleteVisitAction);
        result = gui.getActionResultFromQueue();
        while (result == null) {
            result = gui.getActionResultFromQueue();
        }
        if (result.getActionStatus().equals("OK"))
            viewVisits();
        result = null;
    }
}
