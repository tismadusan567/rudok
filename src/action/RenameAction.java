package action;

import action.dialog.RenameDialog;

import java.awt.event.ActionEvent;

public class RenameAction extends AbstractRudokAction{
    public RenameAction() {
        putValue(NAME, "Rename");
        putValue(SHORT_DESCRIPTION, "Rename");
        putValue(SMALL_ICON, loadIcon("/icons/icons32/edit.png"));

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        new RenameDialog();

    }
}
