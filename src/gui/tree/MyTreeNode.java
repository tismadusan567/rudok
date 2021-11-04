package gui.tree;

import model.RuNode;
import model.RuNodeComposite;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Objects;

public class MyTreeNode extends DefaultMutableTreeNode {
    private RuNode ruNode;
    private final boolean isComposite;

    public MyTreeNode(RuNode ruNode) {
        this.ruNode = ruNode;
        isComposite = ruNode instanceof RuNodeComposite;
        if(isComposite) {
            for(RuNode child : ((RuNodeComposite) ruNode).getChildren()) {
//                children.add(new MyTreeNode(child));
                add(new MyTreeNode(child));
//                System.out.println(child);
            }
        }
    }

    @Override
    public String toString() {
        return ruNode.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyTreeNode that = (MyTreeNode) o;
        return ruNode.equals(that.ruNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ruNode);
    }

    public RuNode getRuNode() {
        return ruNode;
    }

//    @Override
//    public Enumeration<TreeNode> children() {
//        if(isComposite) {
//            return (Enumeration) ((RuNodeComposite) ruNode).getChildren(); //sus
//        }
//        return null;
//    }
//
//    @Override
//    public int getChildCount() {
//        if(isComposite) {
//            return ((RuNodeComposite)ruNode).getChildren().size();
//        }
//        return 0;
//    }
//
//    @Override
//    public int getIndex(TreeNode aChild) {
//        if(isComposite) {
//            return ((RuNodeComposite) ruNode).getChildren().indexOf(((MyTreeNode)aChild).getRuNode());
//        }
//        return -1;
//    }
//
//    @Override
//    public TreeNode getChildAt(int index) {
//        if(children == null) return null;
//        if(isComposite){
//            MyTreeNode toLookFor = new MyTreeNode(((RuNodeComposite) ruNode).getChildren().get(index));
////            for(TreeNode treeNode : children) {
////
////            }
//
//            Iterator<TreeNode> childrenIterator = children.iterator();
//            TreeNode current;
//
//            while (childrenIterator.hasNext()){
//                current = (TreeNode) childrenIterator.next();
//                if (current.equals(toLookFor))
//                    return current;
//            }
//        }
//
//        return null;
//    }
//
//    @Override
//    public boolean getAllowsChildren() {
//        return isComposite;
//    }
//
//    @Override
//    public boolean isLeaf() {
//        return !isComposite;
//    }


}
