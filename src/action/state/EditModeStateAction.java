package action.state;

import action.AbstractRudokAction;
import main.MainFrame;

import java.awt.event.ActionEvent;

public class EditModeStateAction extends AbstractRudokAction {
    public EditModeStateAction() {
        putValue(NAME, "Edit Mode");
        putValue(SHORT_DESCRIPTION, "Edit mode");
//        putValue(SMALL_ICON, loadIcon("/icons/icons32/information.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().getPresentationView().startEditState();
    }
}
