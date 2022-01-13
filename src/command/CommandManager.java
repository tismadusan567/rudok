package command;

import main.MainFrame;

import java.util.ArrayList;

public class CommandManager {
    private final ArrayList<AbstractCommand> commands = new ArrayList<>();
    private int current = 0;

    public void addCommand(AbstractCommand command) {
        while (current < commands.size())
            commands.remove(current);
        commands.add(command);
        doCommand();
    }

    public void doCommand() {
        if (current < commands.size()) {
            commands.get(current++).doCommand();
            MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
        }
        if (current == commands.size()) {
            MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
        }
    }

    public void undoCommand() {
        if (current > 0) {
            MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);
            commands.get(--current).undoCommand();
        }
        if (current == 0) {
            MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
        }
    }

    public void clearCommands() {
        current = 0;
        commands.clear();
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
    }
}
