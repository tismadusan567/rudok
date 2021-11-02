package model;

public class Presentation extends RuNodeComposite{
    private String author;
    private String imagePath;

    public Presentation(String name, RuNode parent, String author, String imagePath) {
        super(name, parent);
        this.author = author;
        this.imagePath = imagePath;
    }

    public Presentation(String name, RuNode parent, String author) {
        this(name, parent, author, "defaultTheme.png"); //todo: add default theme
    }

        @Override
    public void add(RuNode node) {
        if(node instanceof Slide) {
            children.add(node);
        } else {
            //error
            System.out.println("You have to add Slide to Presentation");
        }
    }

    public Slide getSlideAt(int index) {
        //todo: add error checks
        return (Slide) children.get(index);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
