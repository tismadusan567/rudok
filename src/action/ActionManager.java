package action;

public class ActionManager {

    private InfoAction infoAction;
    private NewAction newAction;
    private ChangeAuthorAction changeAuthorAction;
    private ChangeThemeAction changeThemeAction;
    private RenameAction renameAction;
    private RemoveAction removeAction;

    public ActionManager() {
        initActions();
    }

    private void initActions() {
        infoAction = new InfoAction();
        newAction = new NewAction();
        changeAuthorAction = new ChangeAuthorAction();
        changeThemeAction = new ChangeThemeAction();
        renameAction = new RenameAction();
        removeAction = new RemoveAction();
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

    public RemoveAction getRemoveAction() {
        return removeAction;
    }
}
