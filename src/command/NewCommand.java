package command;

import gui.tree.MyTreeNode;
import model.RuNode;

import javax.swing.*;

public class NewCommand extends AbstractCommand {
    private MyTreeNode childTreeNode = null;
    private final MyTreeNode parentTreeNode;
    private final RuNode childRuNode;

    public NewCommand(MyTreeNode parentTreeNode, RuNode childRuNode) {
        this.parentTreeNode = parentTreeNode;
        this.childRuNode = childRuNode;
    }

    @Override
    public void doCommand() {
        if(childTreeNode == null) childTreeNode = new MyTreeNode(childRuNode);
        parentTreeNode.addChild(childTreeNode);
        SwingUtilities.updateComponentTreeUI(tree);
    }

    @Override
    public void undoCommand() {
        childTreeNode.removeFromParent();
        SwingUtilities.updateComponentTreeUI(tree);
    }
}
