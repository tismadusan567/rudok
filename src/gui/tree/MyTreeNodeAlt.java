package gui.tree;

import model.RuNode;
import model.RuNodeComposite;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

public class MyTreeNodeAlt extends DefaultMutableTreeNode {
    public MyTreeNodeAlt(RuNode ruNode) {
        super(ruNode, ruNode instanceof RuNodeComposite);
    }

    @Override
    public int getIndex(TreeNode aChild) {
        return super.getIndex(aChild);
    }
}
