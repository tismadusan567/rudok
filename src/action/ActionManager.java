package action;

import action.state.*;

public class ActionManager {

    private final InfoAction infoAction = new InfoAction();
    private final NewAction newAction = new NewAction();
    private final ChangeAuthorAction changeAuthorAction = new ChangeAuthorAction();
    private final ChangeThemeAction changeThemeAction = new ChangeThemeAction();
    private final RenameAction renameAction = new RenameAction();
    private final RemoveAction removeAction = new RemoveAction();
    private final AddStateAction addStateAction = new AddStateAction();
    private final MoveStateAction moveStateAction = new MoveStateAction();
    private final RemoveStateAction removeStateAction = new RemoveStateAction();
    private final SelectStateAction selectStateAction = new SelectStateAction();
    private final EditModeStateAction editModeStateAction = new EditModeStateAction();
    private final SlideShowStateAction slideShowStateAction = new SlideShowStateAction();
    private final ChooseColorAction chooseColorAction = new ChooseColorAction();
    private final UndoAction undoAction = new UndoAction();
    private final RedoAction redoAction = new RedoAction();
    private final SaveAction saveAction = new SaveAction();
    private final OpenProjectAction openProjectAction = new OpenProjectAction();
    private final OpenWorkspaceAction openWorkspaceAction = new OpenWorkspaceAction();
    private final OpenPresentationAction openPresentationAction = new OpenPresentationAction();
    private final EditSlotAction editSlotAction = new EditSlotAction();
    private final SharePresentationAction sharePresentationAction = new SharePresentationAction();

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

    public EditModeStateAction getEditModeStateAction() {
        return editModeStateAction;
    }

    public SlideShowStateAction getSlideShowStateAction() {
        return slideShowStateAction;
    }

    public ChooseColorAction getChooseColorAction() {
        return chooseColorAction;
    }

    public UndoAction getUndoAction() {
        return undoAction;
    }

    public RedoAction getRedoAction() {
        return redoAction;
    }

    public SaveAction getSaveAction() {
        return saveAction;
    }

    public OpenProjectAction getOpenProjectAction() {
        return openProjectAction;
    }

    public OpenWorkspaceAction getOpenWorkspaceAction() {
        return openWorkspaceAction;
    }

    public OpenPresentationAction getOpenPresentationAction() {
        return openPresentationAction;
    }

    public EditSlotAction getEditSlotAction() {
        return editSlotAction;
    }

    public SharePresentationAction getSharePresentationAction() {
        return sharePresentationAction;
    }
}
