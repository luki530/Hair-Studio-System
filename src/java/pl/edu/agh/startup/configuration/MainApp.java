package pl.edu.agh.startup.configuration;

import pl.edu.agh.actions.Action;
import pl.edu.agh.actions.ActionResult;
import pl.edu.agh.actions.implementations.*;
import pl.edu.agh.persistence.PersistenceManager;
import pl.edu.agh.userInterface.UserInterface;

import java.util.Collection;

public class MainApp implements Runnable {
    private UserInterface userInterface;
    private PersistenceManager persistenceManager;
    private Collection<Action> actions;
    private boolean run;

    public MainApp(MainConfiguration appConfig) {
        userInterface = appConfig.getUserInterface();
        persistenceManager = appConfig.getPersistenceManager();
        actions = appConfig.getActions();
        run = true;
    }

    public UserInterface getUserInterface() {
        return userInterface;
    }

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    public PersistenceManager getPersistenceManager() {
        return persistenceManager;
    }

    public void setPersistenceManager(PersistenceManager persistenceManager) {
        this.persistenceManager = persistenceManager;
    }

    public Collection<Action> getActions() {
        return actions;
    }

    public void setActions(Collection<Action> actions) {
        this.actions = actions;
    }

    @Override
    public void run() {
        while (run) {
            Action action = userInterface.getActionFromQueue();
            if (action != null) {
                ActionResult actionResult = dispatchAction(action);
                userInterface.addActionResultToQueue(actionResult);
            }
        }
    }

    private ActionResult dispatchAction(Action action) {
        String actionClass = action.getClass().getSimpleName();
        switch (actionClass) {
            case "LoginAction":
                LoginAction loginAction = (LoginAction) action;
                loginAction.setParameter("persistenceManager", persistenceManager);
                return loginAction.doOperation();
            case "RegistrationAction":
                RegistrationAction registrationAction = (RegistrationAction) action;
                registrationAction.setParameter("persistenceManager", persistenceManager);
                return registrationAction.doOperation();
            case "LogoutAction":
                LogoutAction logoutAction = (LogoutAction) action;
                return logoutAction.doOperation();
            case "ListUsersAction":
                ListUsersAction listUsersAction = (ListUsersAction) action;
                listUsersAction.setParameter("persistenceManager", persistenceManager);
                return listUsersAction.doOperation();
            case "DeleteUserAction":
                DeleteUserAction deleteUserAction = (DeleteUserAction) action;
                deleteUserAction.setParameter("persistenceManager", persistenceManager);
                return deleteUserAction.doOperation();
            case "ListVisitsAction":
                ListVisitsAction listVisitsAction = (ListVisitsAction) action;
                listVisitsAction.setParameter("persistenceManager", persistenceManager);
                return listVisitsAction.doOperation();
            case "ConfirmVisitAction":
                ConfirmVisitAction confirmVisitAction = (ConfirmVisitAction) action;
                confirmVisitAction.setParameter("persistenceManager", persistenceManager);
                return confirmVisitAction.doOperation();
            case "DeleteVisitAction":
                DeleteVisitAction deleteVisitAction = (DeleteVisitAction) action;
                deleteVisitAction.setParameter("persistenceManager", persistenceManager);
                return deleteVisitAction.doOperation();
            case "ReserveVisitAction":
                ReserveVisitAction reserveVisitAction = (ReserveVisitAction) action;
                reserveVisitAction.setParameter("persistenceManager", persistenceManager);
                return reserveVisitAction.doOperation();
            case "ListServiceAction":
                ListServiceAction listServiceAction = (ListServiceAction) action;
                listServiceAction.setParameter("persistenceManager", persistenceManager);
                return listServiceAction.doOperation();
            default:
                return null;
        }
    }
}

