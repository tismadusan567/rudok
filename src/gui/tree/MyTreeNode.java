package gui.tree;

import model.*;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Objects;

public class MyTreeNode extends DefaultMutableTreeNode {
    private final RuNode ruNode;
    private final boolean isComposite;

    public MyTreeNode(RuNode ruNode) {
        this.ruNode = ruNode;
        isComposite = ruNode instanceof RuNodeComposite;
        if (isComposite) {
            for (RuNode child : ((RuNodeComposite) ruNode).getChildren()) {
                super.add(new MyTreeNode(child));
            }
        }
    }

    public void addChild(RuNode newNode) {
        add(new MyTreeNode(newNode));
        ((RuNodeComposite)ruNode).addChild(newNode);
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

    public int getIndexOfThis() {
        return ((RuNodeComposite) ((MyTreeNode) getParent()).getRuNode()).getChildren().indexOf(this.getRuNode());
    }
}
