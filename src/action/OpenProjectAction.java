package action;

import action.filefilter.ProjectFileFilter;
import command.NewCommand;
import error.ErrorFactory;
import error.ErrorType;
import main.MainFrame;
import model.Project;
import model.Workspace;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class OpenProjectAction extends AbstractRudokAction {

    public OpenProjectAction() {
        putValue(NAME, "Open project");
        putValue(SHORT_DESCRIPTION, "Open project");
//        putValue(SMALL_ICON, loadIcon("/icons/icons32/delete.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new ProjectFileFilter());

        if (jfc.showOpenDialog(MainFrame.getInstance()) != JFileChooser.APPROVE_OPTION) return;

        try {
            ObjectInputStream os = new ObjectInputStream(new FileInputStream(jfc.getSelectedFile()));

            Project project = (Project) os.readObject();
            Workspace workspace = (Workspace) MainFrame.getInstance().getTree().getRootNode().getRuNode();

            MainFrame.getInstance().getCommandManager().addCommand(new NewCommand(project, workspace));

        } catch (IOException | ClassNotFoundException e1) {
            ErrorFactory.getInstance().generateError(ErrorType.ERROR_LOADING_FILE);
        }
    }
}
