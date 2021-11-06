package actions;

public class ActionManager {

    private InfoAction infoAction;
    private NewAction newAction;
    private ChangeAuthorAction changeAuthorAction;
    private ChangeThemeAction changeThemeAction;
    private RenameAction renameAction;

    public ActionManager() {
        initActions();
    }

    private void initActions() {
        infoAction = new InfoAction();
        newAction = new NewAction();
        changeAuthorAction = new ChangeAuthorAction();
        changeThemeAction = new ChangeThemeAction();
        renameAction = new RenameAction();
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

    public RenameAction getRenameAction() {
        return renameAction;
    }
}
