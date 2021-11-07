package model;

public class Project extends RuNodeComposite{
//    public enum Notifications {
//        NEW_PRESENTATION
//    }

    public Project(String name, RuNode parent) {
        super(name, parent);
    }

    @Override
    public void add(RuNode node) {
        if(node instanceof Presentation) {
            children.add(node);
        } else {
            //error
            System.err.println("You have to add Presentation to Project");
            return;
        }
        notify(node);
    }
}
