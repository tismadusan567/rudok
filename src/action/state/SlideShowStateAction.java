package action.state;

import action.AbstractRudokAction;
import main.MainFrame;

import java.awt.event.ActionEvent;

public class SlideShowStateAction extends AbstractRudokAction {
    public SlideShowStateAction() {
        putValue(NAME, "SlideShow");
        putValue(SHORT_DESCRIPTION, "SlideShow");
//        putValue(SMALL_ICON, loadIcon("/icons/icons32/information.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().getPresentationView().startSlideShowState();

    }
}
