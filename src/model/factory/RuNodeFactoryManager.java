package model.factory;

import model.Presentation;
import model.Project;
import model.RuNode;
import model.RuNodeComposite;

public class RuNodeFactoryManager {
    private static final PresentationFactory presentationFactory = new PresentationFactory();
    private static final ProjectFactory projectFactory = new ProjectFactory();
    private static final SlideFactory slideFactory = new SlideFactory();

    public static AbstractRuNodeFactory returnNodeFactory(RuNodeComposite parent) {
        AbstractRuNodeFactory factory;
        if (parent instanceof Presentation) {
            factory = slideFactory;
        } else if (parent instanceof Project) {
            factory = presentationFactory;
        } else {
            factory = projectFactory;
        }
        return factory;
    }
}
