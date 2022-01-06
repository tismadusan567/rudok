package action;

import action.filefilter.MyFileFilter;
import action.filefilter.PresentationFileFilter;
import action.filefilter.ProjectFileFilter;
import action.filefilter.WorkspaceFileFilter;
import gui.tree.MyTree;
import main.MainFrame;
import model.RuNode;
import model.RuNodeComposite;
import model.Workspace;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class SaveAction extends AbstractRudokAction {

    public SaveAction() {
        putValue(NAME, "Save");
        putValue(SHORT_DESCRIPTION, "Save");
//        putValue(SMALL_ICON, loadIcon("/icons/icons32/delete.png"));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTree tree = MainFrame.getInstance().getTree();
        RuNodeComposite nodeToSave;
        MyFileFilter myFileFilter;

        if(tree.getActivePresentationNode() != null) {
            nodeToSave = (RuNodeComposite) tree.getActivePresentationNode().getRuNode();
            myFileFilter = new PresentationFileFilter();
        } else if(tree.getActiveProjectNode() != null) {
            nodeToSave = (RuNodeComposite) tree.getActiveProjectNode().getRuNode();
            myFileFilter = new ProjectFileFilter();
        } else {
            nodeToSave = (RuNodeComposite) tree.getRootNode().getRuNode();
            myFileFilter = new WorkspaceFileFilter();
        }

        if (!nodeToSave.isChanged()) return;

        File file = nodeToSave.getFile();

        if (file == null) {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileFilter(myFileFilter);
            if (jfc.showSaveDialog(MainFrame.getInstance()) != JFileChooser.APPROVE_OPTION) {
                return;
            }
            file = jfc.getSelectedFile();
            if (!file.getAbsolutePath().toLowerCase().endsWith(myFileFilter.getExtension())) {
                file = new File(file.getPath() + myFileFilter.getExtension());
            }
        }
        nodeToSave.setFile(file);

        boolean successfullySaved;
        try {
            if (nodeToSave instanceof Workspace workspace) successfullySaved = saveWorkspace(workspace, file);
            else successfullySaved = saveCollection(nodeToSave, file);
        } catch (IOException exception) {
            exception.printStackTrace();
            successfullySaved = false;
        }

        nodeToSave.setChanged(!successfullySaved);
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
    }

    private boolean saveWorkspace(Workspace nodeToSave, File file) throws FileNotFoundException {
        boolean successfullySaved = true;
        PrintWriter pw = new PrintWriter(file);
        for(RuNode ruNode : nodeToSave.getChildren()) {
            RuNodeComposite child = (RuNodeComposite) ruNode;
            if (child.isChanged() || child.getFile() == null) {
                System.out.println("Failed save: " + child.isChanged() + child.getFile() + child);
                //todo: error, you have to save all projects before saving the workspace
                successfullySaved = false;
                continue;
            }
            pw.println(child.getFile().getAbsolutePath());
        }
        pw.close();
        return successfullySaved;
    }

    private boolean saveCollection(RuNodeComposite nodeToSave, File file) throws IOException {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file));
        os.writeObject(nodeToSave);
        return true;
    }
}
