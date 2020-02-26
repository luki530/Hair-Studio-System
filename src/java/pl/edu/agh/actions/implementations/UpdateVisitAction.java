package pl.edu.agh.actions.implementations;

import pl.edu.agh.actions.Action;
import pl.edu.agh.actions.ActionResult;
import pl.edu.agh.databaseModel.Visit;
import pl.edu.agh.persistence.Persistable;
import pl.edu.agh.persistence.PersistenceManager;

import java.util.List;

public class UpdateVisitAction extends Action {

    @Override
    public ActionResult doOperation() {
        PersistenceManager persistenceManager = (PersistenceManager) getParameter("persistenceManager");
        try {
            Visit visit = new Visit();
            persistenceManager.update(visit);
        } catch (Exception e) {
            e.printStackTrace();
            return new ActionResult("ERROR");
        }
        return null;
    }
}
