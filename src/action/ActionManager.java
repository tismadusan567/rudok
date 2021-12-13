package action;

import action.state.AddStateAction;
import action.state.MoveStateAction;
import action.state.RemoveStateAction;
import action.state.SelectStateAction;

public class ActionManager {

    private InfoAction infoAction;
    private NewAction newAction;
    private ChangeAuthorAction changeAuthorAction;
    private ChangeThemeAction changeThemeAction;
    private RenameAction renameAction;
    private RemoveAction removeAction;
    private AddStateAction addStateAction;
    private MoveStateAction moveStateAction;
    private RemoveStateAction removeStateAction;
    private SelectStateAction selectStateAction;

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
        addStateAction = new AddStateAction();
        moveStateAction = new MoveStateAction();
        removeStateAction = new RemoveStateAction();
        selectStateAction = new SelectStateAction();
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

    public AddStateAction getAddStateAction() {
        return addStateAction;
    }

    public MoveStateAction getMoveStateAction() {
        return moveStateAction;
    }

    public RemoveStateAction getRemoveStateAction() {
        return removeStateAction;
    }

    public SelectStateAction getSelectStateAction() {
        return selectStateAction;
    }
}
