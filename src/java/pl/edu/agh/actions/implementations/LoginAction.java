package pl.edu.agh.actions.implementations;

import pl.edu.agh.actions.Action;
import pl.edu.agh.actions.ActionResult;
import pl.edu.agh.appContext.AppContext;
import pl.edu.agh.databaseModel.User;
import pl.edu.agh.persistence.PersistenceManager;

public class LoginAction extends Action {

    @Override
    public ActionResult doOperation() {
        PersistenceManager persistenceManager = (PersistenceManager) getParameter("persistenceManager");
        String login = (String) getParameter("login");
        User user = new User();
        user = (User) persistenceManager.find(user, login);
        if (user != null) {
            String password = (String) getParameter("password");
            if (user.getPassword().equals(password)) {
                AppContext.setLoggedUser(user);
                return new ActionResult("OK");
            }
        }
        return new ActionResult("ERROR");
    }
}
