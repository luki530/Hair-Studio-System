package pl.edu.agh.actions.implementations;

import pl.edu.agh.actions.Action;
import pl.edu.agh.actions.ActionResult;
import pl.edu.agh.databaseModel.User;
import pl.edu.agh.persistence.Persistable;
import pl.edu.agh.persistence.PersistenceManager;

import java.util.ArrayList;
import java.util.List;

public class ListUsersAction extends Action {

    @Override
    public ActionResult doOperation() {
        try {
            PersistenceManager persistenceManager = (PersistenceManager) getParameter("persistenceManager");
            if (getParameter("role") != null) {
                List<Persistable> users = persistenceManager.findAll(new User());
                List<Persistable> filteredUsers = new ArrayList<>();
                for (Persistable u : users) {
                    User user = (User) u;
                    if (user.getRole().equals(getParameter("role"))) {
                        filteredUsers.add(user);
                    }
                }
                ActionResult actionResult = new ActionResult("OK");
                actionResult.setReturnObject(filteredUsers);
                return actionResult;
            } else {
                List<Persistable> users = persistenceManager.findAll(new User());
                ActionResult actionResult = new ActionResult("OK");
                actionResult.setReturnObject(users);
                return actionResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ActionResult("ERROR");
        }
    }
}
