package action;

import error.ErrorFactory;
import error.ErrorType;
import gui.tree.MyTreeNode;
import main.MainFrame;
import model.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class SaveProjectAction extends AbstractRudokAction {

    public SaveProjectAction() {
        putValue(NAME, "Save project");
        putValue(SHORT_DESCRIPTION, "Save project");
//        putValue(SMALL_ICON, loadIcon("/icons/icons32/delete.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTreeNode projectTreeNode = MainFrame.getInstance().getTree().getActiveProjectNode();
        if (projectTreeNode == null) {
            ErrorFactory.getInstance().generateError(ErrorType.NO_PROJECT_SELECTED);
            return;
        }

        Project project = (Project) projectTreeNode.getRuNode();
        if (!project.isChanged()) return;

        File projectFile = project.getProjectFile();

        if (projectFile == null) {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileFilter(new ProjectFileFilter());
            if (jfc.showSaveDialog(MainFrame.getInstance()) != JFileChooser.APPROVE_OPTION) {
                return;
            }
            projectFile = jfc.getSelectedFile();
            if (!projectFile.getAbsolutePath().toLowerCase().endsWith(".rpf")) {
                projectFile = new File(projectFile.getPath() + ".rpf");
            }
        }

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(projectFile));
            os.writeObject(project);
            project.setProjectFile(projectFile);
            project.setChanged(false);
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}
