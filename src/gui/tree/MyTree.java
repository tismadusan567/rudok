package gui.tree;

import javax.swing.*;
import javax.swing.tree.*;

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
        MyTreeCellRenderer myTreeCellRenderer = new MyTreeCellRenderer();
        setCellRenderer(myTreeCellRenderer);
        setCellEditor(new MyTreeEditor(this, myTreeCellRenderer));
        setEditable(true);
    }

    public MyTreeNode getActiveNode() {
        if(activeSlideNode != null) {
            return activeSlideNode;
        } else if(activePresentationNode != null) {
            return activePresentationNode;
        } else if(activeProjectNode != null) {
            return activeProjectNode;
        }
        return rootNode;
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
