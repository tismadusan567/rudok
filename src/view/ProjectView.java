package view;

import gui.tree.MyTree;
import gui.tree.MyTreeNode;
import main.MainFrame;
import model.Presentation;
import model.Project;
import model.RuNode;
import observer.ISubscriber;

import javax.swing.*;
import javax.swing.tree.TreePath;

public class ProjectView extends JTabbedPane implements ISubscriber {
    private Project project;
    private boolean changeListenerPaused = false;

    public ProjectView() {
        super(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
        addChangeListener(e -> {
            if(changeListenerPaused) return;
            if(getSelectedIndex() == -1) return;

            MyTree tree = MainFrame.getInstance().getTree();
            var projects = tree.getRootNode().children();
            MyTreeNode projectNode = null;
            while (projects.hasMoreElements()) {
                MyTreeNode param = (MyTreeNode) projects.nextElement();
                if(param.getRuNode() == project) projectNode = param;
            }
            if(projectNode == null) {
                System.err.println("ProjectView ChangeListener error");
                return;
            }
            if(projectNode.getChildCount() <= 0) return;
            System.out.println(projectNode  + " " + getSelectedIndex());
            TreePath path = new TreePath(((MyTreeNode)projectNode.getChildAt(getSelectedIndex())).getPath());
            tree.setSelectionPath(path);

//            System.out.println(getSelectedIndex());
        });
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
        } else if(notification instanceof Presentation presentation){
            addTab(presentation.getName(), new PresentationView(presentation));
        } else {
            System.err.println("Wrong notification in ProjectView");;
        }
    }

    public void setChangeListenerPaused(boolean changeListenerPaused) {
        this.changeListenerPaused = changeListenerPaused;
    }

    public Project getProject() {
        return project;
    }
}
