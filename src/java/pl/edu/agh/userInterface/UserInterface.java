package pl.edu.agh.userInterface;

import pl.edu.agh.actions.Action;
import pl.edu.agh.actions.ActionResult;

import java.util.Map;

public interface UserInterface {

    void addActionResultToQueue(ActionResult actionResult);

    ActionResult getActionResultFromQueue();

    Map<String, Object> getParameters();

    Action getActionFromQueue();

    void addActionToQueue(Action action);

    void showError(String prompt);
}
