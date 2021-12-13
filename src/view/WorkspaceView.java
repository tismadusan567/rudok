package view;

import gui.tree.MyTree;
import main.MainFrame;
import model.NotificationEvent;
import model.NotificationTypes;
import model.Project;
import model.Workspace;
import observer.ISubscriber;

import javax.swing.*;
import java.awt.*;

public class WorkspaceView extends JPanel implements ISubscriber {
    private Workspace workspace;
    private final ProjectView projectView;

    public WorkspaceView(ProjectView projectView, Workspace workspace) {
        this.projectView = projectView;
        this.workspace = workspace;
        this.workspace.addSubscriber(this);

        setLayout(new BorderLayout());
        add(projectView, BorderLayout.CENTER);
    }

    @Override
    public void update(NotificationEvent notification) {
        //remove project
//        if (notification instanceof Project) {
//            projectView.reset();
//            MyTree tree = MainFrame.getInstance().getTree();
//            tree.selectNode(tree.getRootNode());
//        }
        if (notification.getType() == NotificationTypes.RUNODECOMPOSITE_REMOVE) {
            projectView.reset();
            MyTree tree = MainFrame.getInstance().getTree();
            tree.selectNode(tree.getRootNode());
        }
    }
}
