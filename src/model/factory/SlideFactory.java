package model.factory;

import model.RuNode;
import model.RuNodeComposite;
import model.Slide;

public class SlideFactory extends AbstractRuNodeFactory{
    @Override
    protected RuNode createNode(RuNodeComposite parent) {
        return new Slide("Slide" + parent.getMaxChildIndex(), parent, parent.getMaxChildIndex());
    }
}
