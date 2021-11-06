package actions;

import gui.tree.MyTree;
import gui.tree.MyTreeNode;
import main.MainFrame;
import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RenameAction extends AbstractRudokAction{
    public RenameAction() {
        putValue(NAME, "Rename");
        putValue(SHORT_DESCRIPTION, "Rename");
        putValue(SMALL_ICON, loadIcon("/res/icons/edit.png"));

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        new RenameDialog();

    }
}
