package model.factory;

import model.Project;
import model.RuNode;
import model.RuNodeComposite;

public class ProjectFactory extends AbstractRuNodeFactory{
    @Override
    protected RuNode createNode(RuNodeComposite parent) {
        return new Project("Project" + parent.getMaxChildIndex(), parent);
    }
}
