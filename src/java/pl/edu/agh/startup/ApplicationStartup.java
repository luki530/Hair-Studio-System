package pl.edu.agh.startup;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.edu.agh.startup.configuration.MainApp;
import pl.edu.agh.startup.configuration.MainConfiguration;
import pl.edu.agh.userInterface.gui.GraphicsUserInterface;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ApplicationStartup extends Application {

    private static final long EXECUTOR_TERMINATION_TIMEOUT = 1L;
    private ExecutorService mainAppExecutor;
    private MainConfiguration config;
    private MainApp mainApp;

    @Override
    public void start(Stage stage) throws Exception {
        GraphicsUserInterface gui = GraphicsUserInterface.getInstance();
        gui.setStage(stage);
        gui.changeSceneTo("loginPage");
        config.setUserInterface(gui);
        mainApp = new MainApp(config);
        mainAppExecutor.execute(mainApp);
    }

    @Override
    public void init() throws Exception {
        mainAppExecutor = Executors.newSingleThreadExecutor();
        config = MainConfiguration.createDevelopmentConfigWithGui();
    }

    @Override
    public void stop() throws Exception {
        mainAppExecutor.awaitTermination(EXECUTOR_TERMINATION_TIMEOUT, TimeUnit.SECONDS);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
