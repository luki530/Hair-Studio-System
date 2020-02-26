package pl.edu.agh.actions.implementations;

import pl.edu.agh.actions.Action;
import pl.edu.agh.actions.ActionResult;
import pl.edu.agh.appContext.AppContext;

public class LogoutAction extends Action {

    @Override
    public ActionResult doOperation() {
        try {
            AppContext.setLoggedUser(null);
            return new ActionResult("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return new ActionResult("ERROR");
        }

    }

}
