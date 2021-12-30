package action;

import main.MainFrame;

import java.awt.event.ActionEvent;

public class UndoAction extends AbstractRudokAction {
    public UndoAction() {
        putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");
//        putValue(SMALL_ICON, loadIcon("/icons/icons32/edit.png"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCommandManager().undoCommand();

    }
}
