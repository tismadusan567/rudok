package model;

import java.util.Objects;

public abstract class RuNode {
    protected String name;
    protected RuNode parent;

    public RuNode(String name, RuNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public RuNode getParent() {
        return parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuNode ruNode = (RuNode) o;
        return Objects.equals(name, ruNode.name) && Objects.equals(parent, ruNode.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parent);
    }
}
