package pl.edu.agh.appContext;

import pl.edu.agh.databaseModel.User;
import pl.edu.agh.databaseModel.Visit;

public class AppContext {

    private static User loggedUser = null;
    private static Visit currentVisit = null;
    private static User currentEmployee = null;

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(User loggedUser) {
        AppContext.loggedUser = loggedUser;
        if (AppContext.getLoggedUser() != null) {
            AppContext.getLoggedUser().refreshPermissions();
        }
    }

    public static Visit getCurrentVisit() {
        return currentVisit;
    }

    public static void setCurrentVisit(Visit currentVisit) {
        AppContext.currentVisit = currentVisit;
        if (AppContext.getLoggedUser() != null) {
            AppContext.getLoggedUser().refreshPermissions();
        }
    }

    public static User getCurrentEmployee() {
        return currentEmployee;
    }

    public static void setCurrentEmployee(User currentEmployee) {
        AppContext.currentEmployee = currentEmployee;
    }
}
