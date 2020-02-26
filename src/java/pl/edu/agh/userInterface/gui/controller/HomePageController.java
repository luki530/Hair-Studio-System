package pl.edu.agh.userInterface.gui.controller;

import com.calendarfx.model.CalendarSource;
import com.calendarfx.model.Entry;
import com.calendarfx.view.CalendarView;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import pl.edu.agh.actions.ActionResult;
import pl.edu.agh.actions.implementations.ListUsersAction;
import pl.edu.agh.actions.implementations.ListVisitsAction;
import pl.edu.agh.actions.implementations.LogoutAction;
import pl.edu.agh.appContext.AppContext;
import pl.edu.agh.databaseModel.User;
import pl.edu.agh.databaseModel.Visit;
import pl.edu.agh.userInterface.gui.GraphicsUserInterface;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static java.time.temporal.TemporalAdjusters.previousOrSame;

//import javafx.scene.control.skin.DatePickerSkin;

public class HomePageController implements Initializable {

    private GraphicsUserInterface gui;
    private ActionResult result;
    private ObservableList projectsList = FXCollections.observableArrayList();
    private User loggedUser;
    private String name = " Home Page";

    @FXML
    private Button showMyVisitsButton;

    @FXML
    private Button showUsersButton;

    @FXML
    private Button logOutButton;

    @FXML
    private Button showServicesButton;

    @FXML
    private Button addNewVisitButton;

    @FXML
    private Button priceListButton;

    @FXML
    CalendarView calendarView;

    @FXML
    private TableView<User> chooseEmployeeTableView;

    @FXML
    private TableColumn<User, String> firstNameTableColumn;

    @FXML
    private TableColumn<User, String> lastNameTableColumn;

    @FXML
    private TextField filterEmployee;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    @FXML
    void showMyVisits(ActionEvent event) {
        gui.changeSceneTo("showMyVisitsPage");
    }

    @FXML
    void showUsers(ActionEvent event) {
        if (AppContext.getLoggedUser().getPermissions().contains("listUsers"))
            gui.changeSceneTo("listUsersPage");
    }

    @FXML
    void employeeInfo(ActionEvent event) {
        gui.changeSceneTo("employeeInfoPage");
    }

    @FXML
    void addNewVisit(ActionEvent event) {
        gui.changeSceneTo("addNewVisitPage");
    }

    @FXML
    void showServices(ActionEvent event) {
        gui.changeSceneTo("showServicesPage");
    }

    @FXML
    void priceList(ActionEvent event) {
        gui.changeSceneTo("priceListPage");

    }


    @FXML
    public void logOut() {
        LogoutAction logoutAction = new LogoutAction();
        gui.addActionToQueue(logoutAction);
        result = gui.getActionResultFromQueue();
        while (result == null) {
            result = gui.getActionResultFromQueue();
        }
        if (result.getActionStatus().equals("OK"))
            gui.changeSceneTo("loginPage");
    }


    @FXML
    private AnchorPane pane;

    private final DatePicker calendarDatePicker = new DatePicker();

//    private void printVisitOnTimeTable(Visit visit){
//        Timestamp ts = visit.getStartTime();
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(ts);
//        int dayOfWeekStart = calendar.get(Calendar.DAY_OF_WEEK)-1;
//        ts = visit.getEndTime();
//        calendar.setTime(ts);
//        int dayOfWeekEnd = calendar.get(Calendar.DAY_OF_WEEK)-1;
//        if(dayOfWeekStart==dayOfWeekEnd){
//            switch(dayOfWeekEnd){
//                case 1:
//                    GraphicsContext gc = monday.getGraphicsContext2D();
//                    gc.strokeRect(0,0,120,30);
//            }
//        }
//
//    }

    @FXML
    AnchorPane mainView;

    private void clearCalendar() {
        calendar.clear();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

//        CalendarView calendarView = new CalendarView();
//        com.calendarfx.model.Calendar calendar = new com.calendarfx.model.Calendar();
//        calendar.setStyle(com.calendarfx.model.Calendar.Style.STYLE1);
//        CalendarSource calendarSource = new CalendarSource("My calendars");
//        calendarSource.getCalendars().add(calendar);
//        calendarView.getCalendarSources().add(calendarSource);
//
//        StackPane sp = new StackPane(calendarView);
//        mainView.getChildren().add(sp);

        loggedUser = AppContext.getLoggedUser();
        showUsersButton.setVisible(loggedUser.getPermissions().contains("listUsers"));

        initializeCalendar();
//        agenda.set

        StackPane root = new StackPane(calendarDatePicker);
        calendarDatePicker.setVisible(false);
        calendarDatePicker.setManaged(false);
        final DatePickerSkin skin = new DatePickerSkin(calendarDatePicker);
        root.getChildren().add(skin.getPopupContent());
        pane.getChildren().add(root);

        String date = new SimpleDateFormat("dd-MM-yyyy").format(Calendar.getInstance().getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        calendarDatePicker.setValue(localDate);

        calendarDatePicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showResults();
            }
        });

        chooseEmployeeTableView.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    User rowData = row.getItem();
                    gui.changeSceneTo("employeeInfoPage");
                } else if (event.getClickCount() == 1 && (!row.isEmpty())) {
                    User rowData = row.getItem();
                    AppContext.setCurrentEmployee(rowData);
                    showResults();
                }
            });
            return row;
        });

        viewEmployees();
        showResults();
    }

    private ObservableList usersList = FXCollections.observableArrayList();

    private void viewEmployees() {
        AppContext.setCurrentEmployee(null);
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
            firstNameTableColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFirstName()));
            lastNameTableColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getLastName()));


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
                    }
                    return false;
                });
            });

            SortedList<User> sortedData = new SortedList<>(filteredData);

            sortedData.comparatorProperty().bind(chooseEmployeeTableView.comparatorProperty());
            chooseEmployeeTableView.setItems(sortedData);
        }
        if (loggedUser.getRole().equals("EMPLOYEE")) {
            chooseEmployeeTableView.getSelectionModel().select(loggedUser);
        } else {
            chooseEmployeeTableView.getSelectionModel().select(0);
        }
        AppContext.setCurrentEmployee(chooseEmployeeTableView.getSelectionModel().getSelectedItem());
    }

    public HomePageController(GraphicsUserInterface gui) {
        this.gui = gui;
    }

    private ObservableList<Visit> visitsList = FXCollections.observableArrayList();

    public static Calendar localDateTimeToCalendar(LocalDateTime localDateTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(localDateTime.getYear(), localDateTime.getMonthValue() - 1, localDateTime.getDayOfMonth(),
                localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond());
        return calendar;
    }

    public void initializeCalendar() {
        calendarView.showWeekPage();
        calendar.setStyle(com.calendarfx.model.Calendar.Style.STYLE1);
        calendarSource.getCalendars().add(calendar);
        calendarView.getCalendarSources().add(calendarSource);
        calendarView.setEntryEditPolicy(entryEditParameter -> false);
        calendarView.setEntryDetailsCallback(entryDetailsParameter -> false);
        calendarView.setEntryContextMenuCallback(entryContextMenuParameter -> new ContextMenu());
        calendarView.setContextMenuCallback(contextMenuParameter -> new ContextMenu());

    }

    CalendarSource calendarSource = new CalendarSource("My Calendar");

    com.calendarfx.model.Calendar calendar = new com.calendarfx.model.Calendar();

    @FXML
    private void showResults() {
        LocalDate date = calendarDatePicker.getValue();
        LocalDate monday = date.with(previousOrSame(MONDAY));
        LocalDate sunday = date.with(nextOrSame(SUNDAY));

        System.out.println("Monday of the Week: " + monday);
        System.out.println("Sunday of the Week: " + sunday);

        Timestamp startWeek = Timestamp.valueOf(monday.atStartOfDay());
        Timestamp endWeek = Timestamp.valueOf(sunday.plusDays(1).atStartOfDay().minusMinutes(1));

        ListVisitsAction action = new ListVisitsAction();
        action.setParameter("weekStart", startWeek);
        action.setParameter("weekEnd", endWeek);
        action.setParameter("employee", AppContext.getCurrentEmployee());

        gui.addActionToQueue(action);
        result = gui.getActionResultFromQueue();
        while (result == null) {
            result = gui.getActionResultFromQueue();
        }
        calendarView.setDate(date);
        clearCalendar();
        List<Visit> visitsList = (List<Visit>) result.getReturnObject();

        if (!visitsList.isEmpty()) {
            for (Visit v : visitsList) {
                printVisitOnCalendar(v);
            }
        }
    }

    private void printVisitOnCalendar(Visit visitToPrint) {
        String title = visitToPrint.getUsersByClient().getFirstName() + " " + visitToPrint.getUsersByClient().getLastName() + "\n" + (visitToPrint.getConfirmed() ? "CONFIRMED" : "RESERVED");
        Entry<Visit> visit = new Entry<>(title);
        visit.changeStartDate(visitToPrint.getStartTime().toLocalDateTime().toLocalDate());
        visit.changeStartTime(visitToPrint.getStartTime().toLocalDateTime().toLocalTime());
        visit.changeEndDate(visitToPrint.getEndTime().toLocalDateTime().toLocalDate());
        visit.changeEndTime(visitToPrint.getEndTime().toLocalDateTime().toLocalTime());
        visit.setUserObject(visitToPrint);
        calendar.addEntry(visit);
    }

}
