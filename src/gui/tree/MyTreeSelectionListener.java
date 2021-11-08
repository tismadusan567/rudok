package gui.tree;

import main.Main;
import main.MainFrame;
import model.Presentation;
import model.Project;
import model.RuNode;
import model.Slide;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class MyTreeSelectionListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        MyTree tree = (MyTree) e.getSource();

        TreePath path = e.getPath();

        tree.setActiveSlideNode(null);
        tree.setActivePresentationNode(null);
        tree.setActiveProjectNode(null);

        //set active components of tree
        for (int i = 0; i < path.getPathCount(); i++) {
            MyTreeNode current = (MyTreeNode) path.getPathComponent(i);
            if (current.getRuNode() instanceof Project) {
                tree.setActiveProjectNode(current);
            } else if (current.getRuNode() instanceof Presentation) {
                tree.setActivePresentationNode(current);
            } else if (current.getRuNode() instanceof Slide) {
                tree.setActiveSlideNode(current);
            }
        }

        MainFrame.getInstance().setViewToTreeSelection();
    }
}
