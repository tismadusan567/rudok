package model;

public class Workspace extends RuNodeComposite {

    public Workspace(String name, RuNodeComposite parent) {
        super(name, parent); // (name, null) if workspace cant have parent
    }

    @Override
    protected void add(RuNode node) {
        if (node instanceof Project) {
            children.add(node);
        } else {
            //error
            System.err.println("You have to add Project to Workspace");
        }
//        notify(NotificationTypes.RUNODECOMPOSITE_ADD);
//        notify(new NotificationEvent(NotificationTypes.RUNODECOMPOSITE_ADD, node));

    }
}
