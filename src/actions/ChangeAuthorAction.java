package actions;

import gui.tree.MyTreeNode;
import main.MainFrame;
import model.Presentation;

import java.awt.event.ActionEvent;

public class ChangeAuthorAction extends AbstractRudokAction{
    public ChangeAuthorAction() {
        putValue(NAME, "Change author");
        putValue(SHORT_DESCRIPTION, "Change author");
        putValue(SMALL_ICON, loadIcon("/res/icons/user.png")); //todo

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTreeNode activePresentationNode = MainFrame.getInstance().getTree().getActivePresentationNode();
        if(activePresentationNode == null) {
            System.out.println("No presentation selected");//todo: popup for this message or error handling
            return;
        }
        new ChangeAuthorDialog((Presentation) activePresentationNode.getRuNode());
    }
}
