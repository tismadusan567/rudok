package action;

import command.RemoveCommand;
import error.ErrorFactory;
import error.ErrorType;
import gui.tree.MyTree;
import main.MainFrame;
import model.RuNode;
import model.Workspace;

import java.awt.event.ActionEvent;

public class RemoveAction extends AbstractRudokAction {
    public RemoveAction() {
        putValue(NAME, "Remove");
        putValue(SHORT_DESCRIPTION, "Remove");
        putValue(SMALL_ICON, loadIcon("/icons/icons32/delete.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTree tree = MainFrame.getInstance().getTree();

        RuNode target = tree.getActiveNode().getRuNode();

        if (target instanceof Workspace) {
            ErrorFactory.getInstance().generateError(ErrorType.REMOVE_WORKSPACE);
            return;
        }
        MainFrame.getInstance().getCommandManager().addCommand(new RemoveCommand(target, target.getParent()));
    }
}
