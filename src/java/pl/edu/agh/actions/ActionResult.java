package pl.edu.agh.actions;

public class ActionResult {

    private String actionStatus;
    private String errorMessage;
    private Object returnObject;

    public ActionResult(String actionStatus) {
        this.actionStatus = actionStatus;
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public ActionResult setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }

    public Object getReturnObject() {
        return returnObject;
    }

    public void setReturnObject(Object returnObject) {
        this.returnObject = returnObject;
    }
}
