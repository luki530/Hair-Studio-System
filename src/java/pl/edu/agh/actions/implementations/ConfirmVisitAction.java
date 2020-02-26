package pl.edu.agh.actions.implementations;

import pl.edu.agh.actions.Action;
import pl.edu.agh.actions.ActionResult;
import pl.edu.agh.databaseModel.Visit;
import pl.edu.agh.persistence.PersistenceManager;

public class ConfirmVisitAction extends Action {

    @Override
    public ActionResult doOperation() {
        try {
            PersistenceManager persistenceManager = (PersistenceManager) getParameter("persistenceManager");
            Visit v = (Visit) getParameter("visit");
            v.setConfirmed(true);
            persistenceManager.update(v);
            return new ActionResult("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return new ActionResult("ERROR");
        }
    }
}
