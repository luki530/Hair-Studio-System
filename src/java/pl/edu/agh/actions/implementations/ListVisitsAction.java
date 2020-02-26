package pl.edu.agh.actions.implementations;

import pl.edu.agh.actions.Action;
import pl.edu.agh.actions.ActionResult;
import pl.edu.agh.databaseModel.User;
import pl.edu.agh.databaseModel.Visit;
import pl.edu.agh.persistence.Persistable;
import pl.edu.agh.persistence.PersistenceManager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ListVisitsAction extends Action {

    @Override
    public ActionResult doOperation() {
        try {
            PersistenceManager persistenceManager = (PersistenceManager) getParameter("persistenceManager");
            if (getParameter("weekStart") != null) {
                Timestamp weekStart = (Timestamp) getParameter("weekStart");
                Timestamp weekEnd = (Timestamp) getParameter("weekEnd");
                User employee = (User) getParameter("employee");

                List<Persistable> visits = persistenceManager.findAll(new Visit());
                List<Persistable> filteredVisits = new ArrayList<>();
                for (Persistable v : visits) {
                    Visit visit = (Visit) v;
                    if (((visit.getStartTime().after(weekStart) && visit.getStartTime().before(weekEnd)) || (visit.getEndTime().after(weekStart) && visit.getEndTime().before(weekEnd))) && visit.getUsersByEmployee() == employee) {
                        filteredVisits.add(visit);
                    }
                }
                ActionResult actionResult = new ActionResult("OK");
                actionResult.setReturnObject(filteredVisits);
                return actionResult;
            } else if (getParameter("currentUser") != null) {
                User user = (User) getParameter("currentUser");
                List<Persistable> visits = persistenceManager.findAll(new Visit());
                List<Persistable> filteredVisits = new ArrayList<>();
                for (Persistable v : visits) {
                    Visit visit = (Visit) v;
                    if (user.getRole().equals("ADMIN") || (visit.getUsersByClient() != null && visit.getUsersByClient().equals(user)) || visit.getUsersByEmployee().equals(user)) {
                        filteredVisits.add(visit);
                    }
                }
                ActionResult actionResult = new ActionResult("OK");
                actionResult.setReturnObject(filteredVisits);
                return actionResult;
            } else {
                List<Persistable> visits = persistenceManager.findAll(new Visit());
                ActionResult actionResult = new ActionResult("OK");
                actionResult.setReturnObject(visits);
                return actionResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ActionResult("ERROR");
        }
    }
}
