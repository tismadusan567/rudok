package action;

import command.NewCommand;
import gui.tree.MyTreeNode;
import main.MainFrame;
import model.Project;

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

        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                ObjectInputStream os = new ObjectInputStream(new FileInputStream(jfc.getSelectedFile()));

                Project project = (Project) os.readObject();
                MyTreeNode workspaceTreeNode = MainFrame.getInstance().getTree().getRootNode();

                MainFrame.getInstance().getCommandManager().addCommand(new NewCommand(workspaceTreeNode, project));

            } catch (IOException | ClassNotFoundException e1) {
                e1.printStackTrace();
            }

        }
    }
}
