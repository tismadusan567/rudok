package gui.tree;

import main.MainFrame;
import model.NotificationEvent;
import model.RuNode;
import model.RuNodeComposite;
import observer.ISubscriber;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Objects;

public class MyTreeNode extends DefaultMutableTreeNode implements ISubscriber {
    private final RuNode ruNode;

    public MyTreeNode(RuNode ruNode) {
        this.ruNode = ruNode;
        ruNode.addSubscriber(this);
        if (ruNode instanceof RuNodeComposite ruNodeComposite) {
            for (RuNode child : ruNodeComposite.getChildren()) {
                super.add(new MyTreeNode(child));
            }
        }
    }

    @Override
    public void update(NotificationEvent notification) {
        switch (notification.getType()) {
            case RUNODECOMPOSITE_ADD -> add(new MyTreeNode((RuNode) notification.getMessage()));
            case RUNODE_REMOVE_FROM_PARENT -> removeFromParent();
        }
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
    }

    @Override
    public void removeFromParent() {
        super.removeFromParent();
        ruNode.removeSubscriber(this);
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
