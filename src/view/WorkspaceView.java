package view;

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
        workspace.addSubscriber(this);

        setLayout(new BorderLayout());
        add(projectView, BorderLayout.CENTER);
    }

    @Override
    public void update(Object notification) {
        //remove project
        if(notification instanceof Project) {
            projectView.reset();
        }
    }
}
