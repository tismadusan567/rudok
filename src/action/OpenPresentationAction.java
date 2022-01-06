package action;

import action.filefilter.PresentationFileFilter;
import command.NewCommand;
import error.ErrorFactory;
import error.ErrorType;
import gui.tree.MyTreeNode;
import main.MainFrame;
import model.Presentation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class OpenPresentationAction extends AbstractRudokAction {

    public OpenPresentationAction() {
        putValue(NAME, "Open presentation");
        putValue(SHORT_DESCRIPTION, "Open presentation");
//        putValue(SMALL_ICON, loadIcon("/icons/icons32/delete.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTreeNode projectTreeNode = MainFrame.getInstance().getTree().getActiveProjectNode();
        if(projectTreeNode == null) {
            ErrorFactory.getInstance().generateError(ErrorType.NO_PROJECT_SELECTED);
            return;
        }

        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new PresentationFileFilter());

        if (jfc.showOpenDialog(MainFrame.getInstance()) != JFileChooser.APPROVE_OPTION) return;

        try {
            ObjectInputStream os = new ObjectInputStream(new FileInputStream(jfc.getSelectedFile()));
            Presentation presentation = (Presentation) os.readObject();

            MainFrame.getInstance().getCommandManager().addCommand(new NewCommand(projectTreeNode, presentation));

        } catch (IOException | ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
