package model;

import java.io.ObjectStreamException;
import java.io.Serial;

public class Project extends RuNodeComposite {

    public Project(String name, RuNodeComposite parent) {
        super(name, parent);
    }

    @Override
    protected void add(RuNode node) {
        if (node instanceof Presentation) children.add(node);
    }

    @Serial
    private Object readResolve() throws ObjectStreamException {
        initTransients();
        return this;
    }
}
