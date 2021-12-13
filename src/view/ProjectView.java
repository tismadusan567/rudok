package view;

import gui.tree.MyTree;
import gui.tree.MyTreeNode;
import main.MainFrame;
import model.*;
import observer.ISubscriber;

import javax.swing.*;
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

        //select current tab in tree
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
            tree.selectNode((MyTreeNode) projectNode.getChildAt(tabbedPane.getSelectedIndex()));
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
            tabbedPane.addTab(presentation.getName(), new PresentationView(presentation, tabbedPane)); //new PresentationView
        }
    }

    public void reset() {
        nameLabel.setText("");
        tabbedPane.removeAll();
        project = null; //
    }

    @Override
    public void update(NotificationEvent notification) {
        //display new project
//        if (notification.getType() instanceof Project) {
//            displayProject((Project) notification);
//        }

        //change name
        if (notification.getType() == NotificationTypes.RUNODE_NAME_CHANGED) {
            nameLabel.setText(project.getName());
        }

        //remove presentation
        if (notification.getType() == NotificationTypes.RUNODECOMPOSITE_REMOVE) {
            Presentation presentation = (Presentation) notification.getMessage();
            int index = -1;
            for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                //find the tab with this presentation
                if (tabbedPane.getComponentAt(i) instanceof PresentationView curr && curr.getPresentation() == presentation) {
                    index = i;
                    break;
                }
            }
            tabbedPane.removeTabAt(index);

            //select project
            MyTree tree = MainFrame.getInstance().getTree();
            tree.selectNode(tree.getActiveProjectNode());
        }

        //add presentation
        if (notification.getType() == NotificationTypes.RUNODECOMPOSITE_ADD) {
            //newest element from children
//            Presentation presentation = (Presentation) project.getChildren().get(project.getChildren().size() - 1);
            Presentation presentation = (Presentation) notification.getMessage();
            tabbedPane.addTab(presentation.getName(), new PresentationView(presentation, tabbedPane));
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

    public PresentationView getPresentationView() {
//        System.out.println(tabbedPane.getSelectedComponent());
        return (PresentationView) tabbedPane.getSelectedComponent();
    }
}
