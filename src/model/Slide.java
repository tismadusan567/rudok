package model;

public class Slide extends RuNode {
    int redniBroj;

    public Slide(String name, RuNodeComposite parent, int redniBroj) {
        super(name, parent);
        this.redniBroj = redniBroj;
    }

    //ne zaboravi notify kad budes dodavo funkcionalnost
}
