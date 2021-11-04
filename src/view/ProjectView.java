package view;

import model.Presentation;
import model.Project;
import model.RuNode;
import observer.ISubscriber;

import javax.swing.*;

public class ProjectView extends JTabbedPane implements ISubscriber {
    private Project project;

    public ProjectView() {
        super(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    public void displayProject(Project project) {
        this.project = project;
        this.project.addSubscriber(this);
        createTabs();
    }

    private void createTabs() {
        removeAll();
        for(RuNode node : project.getChildren()) {
            Presentation presentation = (Presentation) node;
            addTab(presentation.getName(), new PresentationView(presentation)); //new PresentationView
        }
    }

    @Override
    public void update(Object notification) {
        if(notification instanceof Project) {
            displayProject((Project) notification);
        } else {
            System.err.println("Notification not of type Project in ProjectView");
        }
    }
}
