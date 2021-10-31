package model;

import java.util.ArrayList;
import java.util.List;

public abstract class RuNodeComposite extends RuNode {

    protected List<RuNode> children;

    public RuNodeComposite(String name, RuNode parent) {
        super(name, parent);
        children = new ArrayList<>();
    }

    abstract void add(RuNode node);

    void remove(RuNode node) {
        children.remove(node);
    }
}
