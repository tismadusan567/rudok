package action;

import action.dialog.SelectProjectDialog;
import action.filefilter.PresentationFileFilter;
import command.NewCommand;
import error.ErrorFactory;
import error.ErrorType;
import gui.tree.MyTreeNode;
import main.Main;
import main.MainFrame;
import model.Presentation;
import model.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class SharePresentationAction extends AbstractRudokAction {

    public SharePresentationAction() {
        putValue(NAME, "Share presentation");
        putValue(SHORT_DESCRIPTION, "Share presentation");
//        putValue(SMALL_ICON, loadIcon("/icons/icons32/delete.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTreeNode activePresentationNode = MainFrame.getInstance().getTree().getActivePresentationNode();
        if (activePresentationNode == null) {
            ErrorFactory.getInstance().generateError(ErrorType.NO_PRESENTATION_SELECTED);
            return;
        }

        Project project = new SelectProjectDialog(MainFrame.getInstance()).showDialog();
        if (project == null) return;

    }
}
