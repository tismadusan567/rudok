package gui.tree;

import model.Presentation;
import model.Project;
import model.Slide;
import model.Workspace;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class MyTree extends JTree {
    private Workspace root;
    private Project activeProject = null;
    private Presentation activePresentation = null;
    private Slide activeSlide = null;

    public MyTree(Workspace root) {
        super(new DefaultTreeModel(new MyTreeNode(root)));
        this.root = root;
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        addTreeSelectionListener(new MyTreeSelectionListener());
    }

    public Workspace getRoot() {
        return root;
    }

    public void setRoot(Workspace root) {
        this.root = root;
    }

    public Project getActiveProject() {
        return activeProject;
    }

    public void setActiveProject(Project activeProject) {
        this.activeProject = activeProject;
    }

    public Presentation getActivePresentation() {
        return activePresentation;
    }

    public void setActivePresentation(Presentation activePresentation) {
        this.activePresentation = activePresentation;
    }

    public Slide getActiveSlide() {
        return activeSlide;
    }

    public void setActiveSlide(Slide activeSlide) {
        this.activeSlide = activeSlide;
    }
}
