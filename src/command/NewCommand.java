package command;

import model.RuNode;
import model.RuNodeComposite;

public class NewCommand extends AbstractCommand {
    private final RuNodeComposite parent;
    private final RuNode child;

    public NewCommand(RuNode childRuNode, RuNodeComposite parent) {
        this.parent = parent;
        this.child = childRuNode;
    }

    @Override
    public void doCommand() {
        child.setParent(parent);
        parent.addChild(child);
    }

    @Override
    public void undoCommand() {
        child.removeFromParent();
    }
}
