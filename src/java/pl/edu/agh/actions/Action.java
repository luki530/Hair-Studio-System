package pl.edu.agh.actions;

import java.util.HashMap;
import java.util.Map;

public abstract class Action {

    abstract public ActionResult doOperation();

    protected Map<String, Object> parameters = new HashMap<>();

    public Object getParameter(String paramName) {
        return parameters.get(paramName);
    }

    public void setParameter(String paramName, Object paramValue) {
        parameters.put(paramName, paramValue);
    }
}
