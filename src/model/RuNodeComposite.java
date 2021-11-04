package model;

import java.util.ArrayList;
import java.util.List;

public abstract class RuNodeComposite extends RuNode {

    protected List<RuNode> children;

    public RuNodeComposite(String name, RuNode parent) {
        super(name, parent);
        children = new ArrayList<>();
    }

    public abstract void add(RuNode node);

    public void remove(RuNode node) {
        children.remove(node);
        notify(this);
    }

    public List<RuNode> getChildren() {
        return children;
    }
}
