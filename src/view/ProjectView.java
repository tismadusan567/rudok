package view;

import gui.tree.MyTree;
import main.MainFrame;
import model.*;
import observer.ISubscriber;

import javax.swing.*;
import java.awt.*;

public class ProjectView extends JPanel implements ISubscriber {
    private Project project;
    private final JTabbedPane tabbedPane;
    private final JLabel nameLabel;

    public ProjectView() {
        setLayout(new BorderLayout());

        nameLabel = new JLabel("", SwingConstants.CENTER);
        add(nameLabel, BorderLayout.NORTH);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        add(tabbedPane, BorderLayout.CENTER);
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
        switch (notification.getType()) {
            case RUNODE_NAME_CHANGED -> nameLabel.setText(project.getName());
            case RUNODECOMPOSITE_REMOVE -> {
                Presentation presentation = (Presentation) notification.getMessage();
                for (int i = 0; i < tabbedPane.getTabCount(); i++) {
                    //find the tab with this presentation
                    if (tabbedPane.getComponentAt(i) instanceof PresentationView curr && curr.getPresentation() == presentation) {
                        tabbedPane.removeTabAt(i);
                        i--;
                    }
                }
//                if(index != -1) tabbedPane.removeTabAt(index);

                //select project
                MyTree tree = MainFrame.getInstance().getTree();
                if(tree.getActiveProjectNode() != null) tree.selectNode(tree.getActiveProjectNode());
            }
            case RUNODECOMPOSITE_ADD -> {
                Presentation presentation = (Presentation) notification.getMessage();
                if(project == null || project != presentation.getParent()) {
                    displayProject((Project) presentation.getParent());
                    return;
                }
                tabbedPane.addTab(presentation.getName(), new PresentationView(presentation, tabbedPane));
                tabbedPane.validate();
                tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
            }
        }
    }

    public Project getProject() {
        return project;
    }

    public void setSelectedIndex(int indexOfThis) {
        tabbedPane.setSelectedIndex(indexOfThis);
    }

    public PresentationView getPresentationView() {
        return (PresentationView) tabbedPane.getSelectedComponent();
    }
}
