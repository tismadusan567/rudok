package model.factory;

import model.RuNode;
import model.RuNodeComposite;

public abstract class AbstractRuNodeFactory {
    public RuNode getNewNode(RuNodeComposite parent) {
        return createNode(parent);
    }

    protected abstract RuNode createNode(RuNodeComposite parent);
}
