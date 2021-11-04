package view;

public class ActionManager {

    private InfoAction infoAction;
    private NewAction newAction;
    private ChangeAuthorAction changeAuthorAction;
    private ChangeThemeAction changeThemeAction;

    public ActionManager() {
        initActions();
    }

    private void initActions() {
        infoAction = new InfoAction();
        newAction = new NewAction();
        changeAuthorAction = new ChangeAuthorAction();
        changeThemeAction = new ChangeThemeAction();
    }

    public InfoAction getInfoAction() {
        return infoAction;
    }

    public NewAction getNewAction() {
        return newAction;
    }

    public ChangeAuthorAction getChangeAuthorAction() {
        return changeAuthorAction;
    }

    public ChangeThemeAction getChangeThemeAction() {
        return changeThemeAction;
    }
}
