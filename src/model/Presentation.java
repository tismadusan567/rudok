package model;

public class Presentation extends RuNodeComposite{
    public enum Notifications {
        NEW_AUTHOR, NEW_IMAGE_PATH
    }

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
    protected void add(RuNode node) {
        if(node instanceof Slide) {
            children.add(node);
        } else {
            //error
            System.out.println("You have to add Slide to Presentation");
            return;
        }
        notify(node);
    }

    public Slide getSlideAt(int index) {
        //todo: add error checks
        if(children.size() == 0 || index >= children.size()) return null;
        return (Slide) children.get(index);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        notify(Notifications.NEW_AUTHOR);
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        notify(Notifications.NEW_IMAGE_PATH);
    }
}
