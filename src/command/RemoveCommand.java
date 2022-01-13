package command;

import main.MainFrame;
import model.Project;
import model.RuNode;
import model.RuNodeComposite;

public class RemoveCommand extends AbstractCommand{
    private final RuNode child;
    private final RuNodeComposite parent;

    public RemoveCommand(RuNode child, RuNodeComposite parent) {
        this.child = child;
        this.parent = parent;
    }

    @Override
    public void doCommand() {
        child.removeFromParent();
        if(child instanceof Project) MainFrame.getInstance().getProjectView().reset();
    }

    @Override
    public void undoCommand() {
        parent.addChild(child);
    }
}
