package action.state;

import action.AbstractRudokAction;
import main.MainFrame;

import java.awt.event.ActionEvent;

public class SelectStateAction extends AbstractRudokAction {
    public SelectStateAction() {
        putValue(NAME, "Select state");
        putValue(SHORT_DESCRIPTION, "Select state");
//        putValue(SMALL_ICON, loadIcon("/icons/icons32/information.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().getPresentationView().getSlotStateManager().startSelectState();
    }
}
