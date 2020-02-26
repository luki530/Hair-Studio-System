package pl.edu.agh.actions.implementations;

import javafx.scene.control.Alert;
import pl.edu.agh.actions.Action;
import pl.edu.agh.actions.ActionResult;
import pl.edu.agh.databaseModel.User;
import pl.edu.agh.persistence.PersistenceManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationAction extends Action {

    @Override
    public ActionResult doOperation() {
        PersistenceManager persistenceManager = (PersistenceManager) getParameter("persistenceManager");
        try {
            String login = (String) getParameter("login");
            String password = (String) getParameter("password");
            String firstName = (String) getParameter("firstName");
            String lastName = (String) getParameter("lastName");
            String email = (String) getParameter("email");
            String phoneNumber = (String) getParameter("phoneNumber");
            String role = (String) getParameter("role");
            User user = new User();

            user.setLogin(login);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.seteMail(email);
            user.setRole(role);
            user.setPhoneNumber(phoneNumber);
            if (user.toString().contains("\'\'")) {
                throw new Exception();
            }
            persistenceManager.create(user);
            return new ActionResult("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return new ActionResult("ERROR");
        }
    }
}
