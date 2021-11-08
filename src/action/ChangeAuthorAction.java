package action;

import action.dialog.ChangeAuthorDialog;
import error.ErrorFactory;
import gui.tree.MyTreeNode;
import main.MainFrame;
import model.Presentation;

import java.awt.event.ActionEvent;

public class ChangeAuthorAction extends AbstractRudokAction{
    public ChangeAuthorAction() {
        putValue(NAME, "Change author");
        putValue(SHORT_DESCRIPTION, "Change author");
        putValue(SMALL_ICON, loadIcon("/icons/icons32/user.png")); //todo

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTreeNode activePresentationNode = MainFrame.getInstance().getTree().getActivePresentationNode();
        if(activePresentationNode == null) {
//            System.out.println("No presentation selected");//todo: popup for this message or error handling
            ErrorFactory.getInstance().generateError(ErrorFactory.ErrorType.NO_PRESENTATION_SELECTED);
            return;
        }
        new ChangeAuthorDialog((Presentation) activePresentationNode.getRuNode());
    }
}
