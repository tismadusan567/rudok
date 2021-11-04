package view;

import model.Presentation;
import model.Project;
import model.RuNode;

import javax.swing.*;

public class ProjectView extends JTabbedPane {
    private Project project;

    public ProjectView() {
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
            addTab(presentation.getName(), new PresentationView(presentation)); //new PresentationView
        }
    }
}
