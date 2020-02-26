package pl.edu.agh.actions.implementations;

import pl.edu.agh.actions.Action;
import pl.edu.agh.actions.ActionResult;
import pl.edu.agh.appContext.AppContext;
import pl.edu.agh.databaseModel.User;
import pl.edu.agh.databaseModel.Visit;
import pl.edu.agh.objects.exceptions.BusyPeriodException;
import pl.edu.agh.objects.exceptions.InvalidDateException;
import pl.edu.agh.persistence.PersistenceManager;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class ReserveVisitAction extends Action {

    @Override
    public ActionResult doOperation() {
        PersistenceManager persistenceManager = (PersistenceManager) getParameter("persistenceManager");
        try {
            Timestamp startTime = (Timestamp) getParameter("startTime");
            Timestamp endTime = (Timestamp) getParameter("endTime");
            String service = (String) getParameter("service");
            boolean confirmed = (boolean) getParameter("confirmed");
            boolean reserved = (boolean) getParameter("reserved");
            User usersByClient = (User) getParameter("usersByCreator");
            User usersByEmployee = (User) getParameter("usersByEmployee");

            ListVisitsAction listVisitsAction = new ListVisitsAction();
            listVisitsAction.setParameter("persistenceManager", persistenceManager);
            listVisitsAction.setParameter("currentUser", usersByEmployee);
            ActionResult listVisitsActionResult = listVisitsAction.doOperation();
            List<Visit> employeeVisits = (List) listVisitsActionResult.getReturnObject();
            for (Visit v : employeeVisits) {
                if (startTime.before(Timestamp.valueOf(LocalDateTime.now()))) {
                    throw new InvalidDateException();
                } else if ((startTime.after(v.getStartTime()) && startTime.before(v.getEndTime())) ||
                        (endTime.after(v.getStartTime()) && endTime.before(v.getEndTime()))) {
                    throw new BusyPeriodException();
                }
            }

            Visit visit = new Visit();
            visit.setStartTime(startTime);
            visit.setEndTime(endTime);
            visit.setService(service);
            visit.setConfirmed(confirmed);
            visit.setBusy(reserved);
            visit.setUsersByClient(usersByClient);
            visit.setUsersByEmployee(usersByEmployee);
            visit.setUsersByCreator(AppContext.getLoggedUser());

            persistenceManager.create(visit);
            return new ActionResult("OK");

        } catch (BusyPeriodException e) {
            return new ActionResult("ERROR").setErrorMessage("CHOSEN VISIT TIME IS BUSY. \n CHOOSE ANOTHER TIME OR ANOTHER EMPLOYEE.");
        } catch (InvalidDateException e) {
            return new ActionResult("ERROR").setErrorMessage("YOU CANNOT RESERVE VISIT BEFORE NOW ");
        } catch (NullPointerException e) {
            return new ActionResult("ERROR").setErrorMessage("SOMETHING WENT WRONG. \n MAKE SURE THAT YOU HAVE FILLED IN EVERY FIELD");
        } catch (Exception e) {
            e.printStackTrace();
            return new ActionResult("ERROR");
        }
    }
}
