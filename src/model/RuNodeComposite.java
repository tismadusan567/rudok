package model;

import java.util.ArrayList;
import java.util.List;

public abstract class RuNodeComposite extends RuNode {

    protected List<RuNode> children;

    public RuNodeComposite(String name, RuNode parent) {
        super(name, parent);
        children = new ArrayList<>();
    }

    public void add(RuNode node) {
        children.add(node);
    }

    public void remove(RuNode node) {
        children.remove(node);
    }

    public List<RuNode> getChildren() {
        return children;
    }
}
