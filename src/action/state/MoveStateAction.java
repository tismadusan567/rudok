package action.state;

import action.AbstractRudokAction;
import main.MainFrame;

import java.awt.event.ActionEvent;

public class MoveStateAction extends AbstractRudokAction {
    public MoveStateAction() {
        putValue(NAME, "Move state");
        putValue(SHORT_DESCRIPTION, "Move state");
//        putValue(SMALL_ICON, loadIcon("/icons/icons32/information.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().getPresentationView().startMoveState();
    }
}
