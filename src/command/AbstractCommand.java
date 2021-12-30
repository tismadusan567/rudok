package command;

import gui.tree.MyTree;
import main.MainFrame;
import model.RuNode;

public abstract class AbstractCommand {
    protected MyTree tree = MainFrame.getInstance().getTree();

    public abstract void doCommand();

    public abstract void undoCommand();
}
