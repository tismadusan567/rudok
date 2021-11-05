package gui.tree;

import model.Presentation;
import model.Project;
import model.Slide;
import model.Workspace;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

public class MyTree extends JTree {
    private MyTreeNode rootNode;

    private MyTreeNode activeProjectNode = null;
    private MyTreeNode activePresentationNode = null;
    private MyTreeNode activeSlideNode = null;

    public MyTree(MyTreeNode rootNode) {
        super(new DefaultTreeModel(rootNode));
        this.rootNode = rootNode;
        getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        addTreeSelectionListener(new MyTreeSelectionListener());
    }

    public MyTreeNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(MyTreeNode rootNode) {
        this.rootNode = rootNode;
    }

    public MyTreeNode getActiveProjectNode() {
        return activeProjectNode;
    }

    public void setActiveProjectNode(MyTreeNode activeProjectNode) {
        this.activeProjectNode = activeProjectNode;
    }

    public MyTreeNode getActivePresentationNode() {
        return activePresentationNode;
    }

    public void setActivePresentationNode(MyTreeNode activePresentationNode) {
        this.activePresentationNode = activePresentationNode;
    }

    public MyTreeNode getActiveSlideNode() {
        return activeSlideNode;
    }

    public void setActiveSlideNode(MyTreeNode activeSlideNode) {
        this.activeSlideNode = activeSlideNode;
    }
}