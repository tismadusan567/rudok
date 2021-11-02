package temp;

import model.Presentation;
import model.Project;
import model.RuNode;

import javax.swing.*;

public class ProjectTabbedPane extends JTabbedPane {
    private Project project;

    public ProjectTabbedPane() {
        super(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    public void displayProject(Project project) {
        this.project = project;
        createTabs();
    }

    private void createTabs() {
        removeAll();
        for(RuNode node : project.getChildren()) {
            Presentation presentation = (Presentation) node;
            addTab(presentation.getName(), new PresentationPanel(presentation)); //new PresentationPanel
        }
    }
}
