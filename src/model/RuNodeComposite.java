package model;

import java.util.ArrayList;
import java.util.List;

public abstract class RuNodeComposite extends RuNode {

    protected final List<RuNode> children;
    private int lastChild = 1;

    public RuNodeComposite(String name, RuNode parent) {
        super(name, parent);
        children = new ArrayList<>();
    }

    protected abstract void add(RuNode node);

    public void addChild(RuNode node) {
        add(node);
        lastChild++;
    }

    public void remove(RuNode node) {
        children.remove(node);
        notify(this);
    }

    public List<RuNode> getChildren() {
        return children;
    }

    public int getLastChild() {
        return lastChild;
    }
}
