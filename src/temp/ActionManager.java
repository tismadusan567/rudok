package temp;

public class ActionManager {

    private InfoAction infoAction;
    private NewAction newAction;

    public ActionManager() {
        initActions();
    }

    private void initActions() {
        infoAction = new InfoAction();
        newAction = new NewAction();
    }

    public InfoAction getInfoAction() {
        return infoAction;
    }

    public NewAction getNewAction() {
        return newAction;
    }
}
