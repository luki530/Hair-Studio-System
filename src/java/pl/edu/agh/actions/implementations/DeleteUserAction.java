package pl.edu.agh.actions.implementations;

import pl.edu.agh.actions.Action;
import pl.edu.agh.actions.ActionResult;
import pl.edu.agh.appContext.AppContext;
import pl.edu.agh.databaseModel.User;
import pl.edu.agh.databaseModel.Visit;
import pl.edu.agh.persistence.PersistenceManager;

import java.util.List;

public class DeleteUserAction extends Action {

    @Override
    public ActionResult doOperation() {
        try {
            User user = (User) getParameter("user");
            if (user == AppContext.getLoggedUser()) {
                throw new Exception();
            }
            PersistenceManager persistenceManager = (PersistenceManager) getParameter("persistenceManager");

            ListVisitsAction listVisitsAction = new ListVisitsAction();
            listVisitsAction.setParameter("persistenceManager", persistenceManager);
            ActionResult result = listVisitsAction.doOperation();
            List<Visit> visits = (List) result.getReturnObject();
            for (Visit v : visits) {
                if (v.getUsersByEmployee().equals(user) || v.getUsersByClient().equals(user)) {
                    DeleteVisitAction deleteVisitAction = new DeleteVisitAction();
                    deleteVisitAction.setParameter("persistenceManager", persistenceManager);
                    deleteVisitAction.setParameter("visit", v);
                    deleteVisitAction.doOperation();
                } else if (v.getUsersByCreator().equals(user)) {
                    UpdateVisitAction updateVisitAction = new UpdateVisitAction();
                    updateVisitAction.setParameter("persistenceManager", persistenceManager);
                    Visit visit = v;
                    visit.setUsersByCreator(null);
                    updateVisitAction.setParameter("visit", visit);
                    updateVisitAction.doOperation();
                }
            }
            persistenceManager.delete(user);
            return new ActionResult("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return new ActionResult("ERROR");
        }
    }
}
