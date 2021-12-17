package action;

import error.ErrorFactory;
import gui.tree.MyTree;
import gui.tree.MyTreeNode;
import main.MainFrame;
import model.*;
import model.factory.RuNodeFactoryManager;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewAction extends AbstractRudokAction {
    public NewAction() {
        putValue(NAME, "New");
        putValue(SHORT_DESCRIPTION, "New");
        putValue(SMALL_ICON, loadIcon("/icons/icons32/add.png"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTree tree = MainFrame.getInstance().getTree();

        MyTreeNode target = tree.getActiveNode();
        RuNode targetRuNode = target.getRuNode();

        if (targetRuNode instanceof Slide) {
            ErrorFactory.getInstance().generateError(ErrorFactory.ErrorType.ADD_TO_SLIDE);
            return;
        }

        RuNodeComposite targetRuNodeComposite = (RuNodeComposite) targetRuNode;
        RuNode newNode = RuNodeFactoryManager.returnNodeFactory(targetRuNodeComposite).getNewNode(targetRuNodeComposite);
        target.addChild(newNode);

        SwingUtilities.updateComponentTreeUI(tree);
    }
}
