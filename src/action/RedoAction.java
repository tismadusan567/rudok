package action;

import main.MainFrame;

import java.awt.event.ActionEvent;

public class RedoAction extends AbstractRudokAction {
    public RedoAction() {
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo");
//        putValue(SMALL_ICON, loadIcon("/icons/icons32/edit.png"));
        setEnabled(false);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCommandManager().doCommand();

    }
}
