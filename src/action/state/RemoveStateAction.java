package action.state;

import action.AbstractRudokAction;
import main.MainFrame;

import java.awt.event.ActionEvent;

public class RemoveStateAction extends AbstractRudokAction {
    public RemoveStateAction() {
        putValue(NAME, "Remove state");
        putValue(SHORT_DESCRIPTION, "Remove state");
//        putValue(SMALL_ICON, loadIcon("/icons/icons32/information.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().getPresentationView().getSlotStateManager().startRemoveState();
    }
}
