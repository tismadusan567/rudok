package action.state;

import action.AbstractRudokAction;
import main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddStateAction extends AbstractRudokAction {
    public AddStateAction() {
        putValue(NAME, "Add state");
        putValue(SHORT_DESCRIPTION, "Add state");
//        putValue(SMALL_ICON, loadIcon("/icons/icons32/information.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().getPresentationView().getSlotStateManager().startAddState();
    }
}
