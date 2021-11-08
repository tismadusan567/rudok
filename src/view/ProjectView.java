package view;

import gui.tree.MyTree;
import gui.tree.MyTreeNode;
import main.MainFrame;
import model.Notifications;
import model.Presentation;
import model.Project;
import model.RuNode;
import observer.ISubscriber;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;

public class ProjectView extends JPanel implements ISubscriber {
    private Project project;
    private final JTabbedPane tabbedPane;
    private final JLabel nameLabel;
    private boolean changeListenerPaused = false;

    public ProjectView() {
        setLayout(new BorderLayout());

        nameLabel = new JLabel("", SwingConstants.CENTER);
        add(nameLabel, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        add(tabbedPane, BorderLayout.CENTER);
        tabbedPane.addChangeListener(e -> {
            if (changeListenerPaused) return;
            if (tabbedPane.getSelectedIndex() == -1) return;

            MyTree tree = MainFrame.getInstance().getTree();
            var projects = tree.getRootNode().children();
            MyTreeNode projectNode = null;
            while (projects.hasMoreElements()) {
                MyTreeNode param = (MyTreeNode) projects.nextElement();
                if (param.getRuNode() == project) projectNode = param;
            }
            if (projectNode == null) {
                System.err.println("ProjectView ChangeListener error");
                return;
            }
            if (projectNode.getChildCount() <= 0) return;
            System.out.println(projectNode + " " + tabbedPane.getSelectedIndex());
            TreePath path = new TreePath(((MyTreeNode) projectNode.getChildAt(tabbedPane.getSelectedIndex())).getPath());
            tree.setSelectionPath(path);
        });
    }

    public void displayProject(Project project) {
        this.project = project;
        this.project.addSubscriber(this);

        nameLabel.setText(project.getName());
        createTabs();
    }

    private void createTabs() {
        tabbedPane.removeAll();
        for (RuNode node : project.getChildren()) {
            Presentation presentation = (Presentation) node;
            tabbedPane.addTab(presentation.getName(), new PresentationView(presentation)); //new PresentationView
        }
    }

    @Override
    public void update(Object notification) {
        //display new project
        if (notification instanceof Project) {
            displayProject((Project) notification);
        }

        //change name
        if (notification == Notifications.RUNODE_NAME_CHANGED) {
            nameLabel.setText(project.getName());
        }

        //remove presentation
        if (notification instanceof Presentation presentation) {

        }

        //add presentation
        if (notification == Notifications.RUNODECOMPOSITE_ADD) {
            //newest element from children
            Presentation presentation = (Presentation) project.getChildren().get(project.getChildren().size() - 1);
            tabbedPane.addTab(presentation.getName(), new PresentationView(presentation));
            tabbedPane.validate();
        }
    }

    public void setChangeListenerPaused(boolean changeListenerPaused) {
        this.changeListenerPaused = changeListenerPaused;
    }

    public Project getProject() {
        return project;
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void setSelectedIndex(int indexOfThis) {
        tabbedPane.setSelectedIndex(indexOfThis);
    }

    public int getTabCount() {
        return tabbedPane.getTabCount();
    }
}
