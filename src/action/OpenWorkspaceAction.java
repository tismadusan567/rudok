package action;

import action.filefilter.WorkspaceFileFilter;
import error.ErrorFactory;
import error.ErrorType;
import gui.tree.MyTreeNode;
import main.MainFrame;
import model.Project;
import model.Workspace;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class OpenWorkspaceAction extends AbstractRudokAction {

    public OpenWorkspaceAction() {
        putValue(NAME, "Open workspace");
        putValue(SHORT_DESCRIPTION, "Open workspace");
//        putValue(SMALL_ICON, loadIcon("/icons/icons32/delete.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();
        WorkspaceFileFilter workspaceFileFilter = new WorkspaceFileFilter();
        jfc.setFileFilter(workspaceFileFilter);

        if (jfc.showOpenDialog(MainFrame.getInstance()) != JFileChooser.APPROVE_OPTION) return;

        try {
            File file = jfc.getSelectedFile();
            BufferedReader br = new BufferedReader(new FileReader(file));

            Workspace newWorkspace = new Workspace(file.getName().replace(workspaceFileFilter.getExtension(), ""));
            newWorkspace.setFile(file);
            MyTreeNode workspaceTreeNode = new MyTreeNode(newWorkspace);
            MainFrame.getInstance().getTree().setRootNode(workspaceTreeNode);

            String line;
            while((line = br.readLine()) != null) {
                try {
                    ObjectInputStream os = new ObjectInputStream(new FileInputStream(line));
                    Project project =  (Project) os.readObject();
                    newWorkspace.addChild(project);
                    project.setParent(newWorkspace);
                } catch (ClassNotFoundException | FileNotFoundException ex) {
                    ErrorFactory.getInstance().generateError(ErrorType.ERROR_LOADING_FILE);
                }
            }

            MainFrame.getInstance().getCommandManager().clearCommands();
            MainFrame.getInstance().getProjectView().reset();
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
        } catch (IOException ex) {
            ex.printStackTrace();
            ErrorFactory.getInstance().generateError(ErrorType.ERROR_LOADING_FILE);
        }
    }
}
