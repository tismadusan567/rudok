package command;

import gui.tree.MyTreeNode;
import main.MainFrame;
import model.RuNode;

import javax.swing.*;

public class RemoveCommand extends AbstractCommand{
    private final MyTreeNode childTreeNode;
    private final MyTreeNode parentTreeNode;

    public RemoveCommand(MyTreeNode parentTreeNode, MyTreeNode childTreeNode) {
        this.parentTreeNode = parentTreeNode;
        this.childTreeNode = childTreeNode;
    }

    @Override
    public void doCommand() {
        childTreeNode.removeFromParent();
        SwingUtilities.updateComponentTreeUI(tree);
    }

    @Override
    public void undoCommand() {
        parentTreeNode.addChild(childTreeNode);
        SwingUtilities.updateComponentTreeUI(tree);
    }
}
