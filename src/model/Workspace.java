package model;

public class Workspace extends RuNodeComposite{

    public Workspace(String name, RuNode parent) {
        super(name, parent); // (name, null) if workspace cant have parent
    }

    @Override
    public void add(RuNode node) {
        if(node instanceof Project) {
            children.add(node);
        } else {
            //error
            System.out.println("You have to add Project to Workspace");
        }
    }
}
