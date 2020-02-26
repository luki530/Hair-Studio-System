package pl.edu.agh.userInterface.gui;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import pl.edu.agh.actions.Action;
import pl.edu.agh.actions.ActionResult;
import pl.edu.agh.appContext.AppContext;
import pl.edu.agh.userInterface.UserInterface;
import pl.edu.agh.userInterface.gui.controller.*;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class GraphicsUserInterface implements UserInterface {

    private static GraphicsUserInterface instance = new GraphicsUserInterface();

    private Stage stage;
    private BlockingQueue<ActionResult> actionsResults;
    private BlockingQueue<Action> actions;

    private GraphicsUserInterface() {
        actionsResults = new LinkedBlockingQueue<>();
        actions = new LinkedBlockingQueue<>();
    }

    public static GraphicsUserInterface getInstance() {
        return instance;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void addActionResult(ActionResult actionResult) {
        actionsResults.add(actionResult);
    }

    private void setScene(Object controller, URL fxmlURL,String name) {
        FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
        fxmlLoader.setController(controller);
        AnchorPane root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.show();
        stage.setOnCloseRequest(e -> Platform.exit());
        stage.setTitle(name);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
    }

    public void changeSceneTo(String sceneName) {
        if (AppContext.getLoggedUser() != null)
            AppContext.getLoggedUser().refreshPermissions();

        switch (sceneName) {
            case "loginPage":
                LoginPageController loginPageController = new LoginPageController(this);
                URL loginPageUrl = getClass().getClassLoader().getResource("fxml/LoginPage.fxml");
                String loginPageControllerName = loginPageController.getName();
                setScene(loginPageController, loginPageUrl,loginPageControllerName);
                break;
            case "registrationPage":
                RegistrationPageController registrationPageController = new RegistrationPageController(this);
                URL registrationPageUrl = getClass().getClassLoader().getResource("fxml/RegistrationPage.fxml");
                String registrationPageControllerName = registrationPageController.getName();
                setScene(registrationPageController, registrationPageUrl,registrationPageControllerName);
                break;
            case "homePage":
                HomePageController homePageController = new HomePageController(this);
                URL homePageUrl = getClass().getClassLoader().getResource("fxml/HomePage.fxml");
                String homePageName = homePageController.getName();
                setScene(homePageController, homePageUrl,homePageName);
                break;
            case "listUsersPage":
                ListUsersPageController listUsersPageController = new ListUsersPageController(this);
                URL listUsersPageUrl = getClass().getClassLoader().getResource("fxml/ListUsersPage.fxml");
                String listUsersPageName = listUsersPageController.getName();
                setScene(listUsersPageController, listUsersPageUrl,listUsersPageName);
                break;
            case "showMyVisitsPage":
                ShowMyVisitsPageController showMyVisitsPageController = new ShowMyVisitsPageController(this);
                URL showMyVisitsPageUrl = getClass().getClassLoader().getResource("fxml/ShowMyVisitsPage.fxml");
                String showMyVisitsPageControllerName = showMyVisitsPageController.getName();
                setScene(showMyVisitsPageController, showMyVisitsPageUrl,showMyVisitsPageControllerName);
                break;
            case "employeeInfoPage":
                EmployeeInfoPageController employeeInfoPageController = new EmployeeInfoPageController(this);
                URL employeeInfoPageUrl = getClass().getClassLoader().getResource("fxml/EmployeeInfoPage.fxml");
                String employeeInfoPageControllerName = employeeInfoPageController.getName();
                setScene(employeeInfoPageController, employeeInfoPageUrl,employeeInfoPageControllerName);
                break;
            case "addNewVisitPage":
                AddNewVisitPageController addNewVisitPageController = new AddNewVisitPageController(this);
                URL addNewVisitPageUrl = getClass().getClassLoader().getResource("fxml/AddNewVisitPage.fxml");
                String addNewVisitPageControllerName = addNewVisitPageController.getName();
                setScene(addNewVisitPageController, addNewVisitPageUrl,addNewVisitPageControllerName);
                break;
            case "showServicesPage":
                ShowServicesPageController showServicesPageController = new ShowServicesPageController(this);
                URL showServicesPageUrl = getClass().getClassLoader().getResource("fxml/ShowServicesPage.fxml");
                String showServicesPageControllerName = showServicesPageController.getName();
                setScene(showServicesPageController, showServicesPageUrl,showServicesPageControllerName);
                break;
            case "priceListPage":
                PriceListPageController priceListPageController = new PriceListPageController(this);
                URL priceListPageUrl = getClass().getClassLoader().getResource("fxml/PriceListPage.fxml");
                String priceListPageControllerName = priceListPageController.getName();
                setScene(priceListPageController, priceListPageUrl,priceListPageControllerName);
                break;
            default:
                break;
        }
    }

    @Override
    public Map<String, Object> getParameters() {
        return null;
    }

    @Override
    public Action getActionFromQueue() {
        return actions.poll();
    }

    @Override
    public void addActionToQueue(Action action) {
        actions.add(action);
        System.out.println(action.toString());
    }

    @Override
    public void addActionResultToQueue(ActionResult actionResult) {
        actionsResults.add(actionResult);
    }

    @Override
    public ActionResult getActionResultFromQueue() {
        return actionsResults.poll();
    }

    @Override
    public void showError(String prompt) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Button button = new Button("OK");
        button.setOnAction(new EventHandler() {
            @Override
            public void handle(Event e) {
                dialogStage.close();
            }
        });
        VBox vbox = new VBox(new Text(prompt), button);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(15));

        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();
    }
}
