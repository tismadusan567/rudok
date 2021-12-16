package action;

import action.dialog.ChangeAuthorDialog;
import error.ErrorFactory;
import gui.tree.MyTreeNode;
import main.MainFrame;
import model.Presentation;

import java.awt.event.ActionEvent;

public class ChooseColorAction extends AbstractRudokAction{
    public ChooseColorAction() {
        putValue(NAME, "Choose color");
        putValue(SHORT_DESCRIPTION, "Choose color");
//        putValue(SMALL_ICON, loadIcon("/icons/icons32/user.png")); //todo

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().getPresentationView().chooseColor();
    }
}
