package model;

import java.io.ObjectStreamException;
import java.io.Serial;

public class Workspace extends RuNodeComposite {

    public Workspace(String name) {
        super(name, null);
    }

    @Override
    protected void add(RuNode node) {
        if (node instanceof Project) children.add(node);
    }

    @Serial
    private Object readResolve() throws ObjectStreamException {
        initTransients();
        return this;
    }
}
