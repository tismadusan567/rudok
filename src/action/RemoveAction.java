package action;

import gui.tree.MyTree;
import gui.tree.MyTreeNode;
import main.MainFrame;
import model.*;

import javax.swing.*;
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

        MyTreeNode target = tree.getActiveNode();
        RuNode targetRuNode = target.getRuNode();

        target.removeFromParent();
        if (targetRuNode.getParent() instanceof RuNodeComposite)
            ((RuNodeComposite) targetRuNode.getParent()).remove(targetRuNode);

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
    }
}
