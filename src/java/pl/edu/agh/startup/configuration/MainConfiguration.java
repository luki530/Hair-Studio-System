package pl.edu.agh.startup.configuration;

import pl.edu.agh.actions.Action;
import pl.edu.agh.persistence.PersistenceManager;
import pl.edu.agh.persistence.sql.JPAPersistenceManager;
import pl.edu.agh.userInterface.UserInterface;

import java.util.Collection;
import java.util.LinkedList;

public class MainConfiguration {

    private UserInterface userInterface;
    private PersistenceManager persistenceManager;

    private Collection<Action> actions;

    private MainConfiguration() {

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

    public static MainConfiguration createDevelopmentConfigWithGui() {
        MainConfiguration config = new MainConfiguration();
        PersistenceManager persistenceManager = JPAPersistenceManager.getInstance();
        config.setPersistenceManager(persistenceManager);
        config.setActions(initializeActions());
        return config;
    }

    private static Collection<Action> initializeActions() {

        Collection<Action> actions = new LinkedList<Action>();
        // actions.add(new ShowProjectsAction());
        // actions.add(new CreateDocumentAction());

        return actions;

    }
}
