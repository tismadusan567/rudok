package model.factory;

import model.Presentation;
import model.RuNode;
import model.RuNodeComposite;

public class PresentationFactory extends AbstractRuNodeFactory {

    @Override
    protected RuNode createNode(RuNodeComposite parent) {
        return new Presentation(
                "Presentation" + parent.getMaxChildIndex(),
                parent,
                "Author",
                "/backgrounds/background.jpeg"
        );
    }
}
