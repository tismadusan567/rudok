package gui.tree;

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

        tree.setActiveSlide(null);
        tree.setActivePresentation(null);
        tree.setActiveProject(null);

        //set active components of tree
        for(int i=0; i<path.getPathCount(); i++) {
            RuNode current = ((MyTreeNode)path.getPathComponent(i)).getRuNode();
            if(current instanceof Project) {
                tree.setActiveProject((Project) current);
            } else if(current instanceof Presentation) {
                tree.setActivePresentation((Presentation) current);
            } else if(current instanceof Slide) {
                tree.setActiveSlide((Slide) current);
            }
        }

//        System.out.println(tree.getActiveProject() + " " + tree.getActivePresentation() + " " + tree.getActiveSlide());
    }
}
