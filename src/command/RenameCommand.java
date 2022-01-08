package command;

import model.RuNode;

import javax.swing.*;

public class RenameCommand extends AbstractCommand{
    private final RuNode ruNode;
    private final String prevName;
    private final String newName;

    public RenameCommand(RuNode ruNode, String prevName, String newName) {
        this.ruNode = ruNode;
        this.prevName = prevName;
        this.newName = newName;
    }

    @Override
    public void doCommand() {
        ruNode.setName(newName);
        SwingUtilities.updateComponentTreeUI(tree);
    }

    @Override
    public void undoCommand() {
        ruNode.setName(prevName);
        SwingUtilities.updateComponentTreeUI(tree);
    }
}
