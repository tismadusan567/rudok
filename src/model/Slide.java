package model;

public class Slide extends RuNode{
    int redniBroj;
    public Slide(String name, RuNode parent, int redniBroj) {
        super(name, parent);
        this.redniBroj = redniBroj;
    }
}
