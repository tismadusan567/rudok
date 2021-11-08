package model;

public class Project extends RuNodeComposite {

    public Project(String name, RuNodeComposite parent) {
        super(name, parent);
    }

    @Override
    protected void add(RuNode node) {
        if (node instanceof Presentation) {
            children.add(node);
        } else {
            //error
            System.err.println("You have to add Presentation to Project");
            return;
        }
        notify(Notifications.RUNODECOMPOSITE_ADD);
    }
}
