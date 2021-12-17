package model;

public class Workspace extends RuNodeComposite {

    public Workspace(String name) {
        super(name, null);
    }

    @Override
    protected void add(RuNode node) {
        if (node instanceof Project) {
            children.add(node);
        } else {
            //error
            System.err.println("You have to add Project to Workspace");
        }
    }
}
