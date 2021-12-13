package model;

public class Presentation extends RuNodeComposite {

    private String author;
    private String imagePath;

    public Presentation(String name, RuNodeComposite parent, String author, String imagePath) {
        super(name, parent);
        this.author = author;
        this.imagePath = imagePath;
    }

    public Presentation(String name, RuNodeComposite parent, String author) {
        this(name, parent, author, "defaultTheme.png"); //todo: add default theme
    }

    @Override
    protected void add(RuNode node) {
        if (node instanceof Slide) {
            children.add(node);
        } else {
            //error
            System.out.println("You have to add Slide to Presentation");
            return;
        }
//        notify(NotificationTypes.RUNODECOMPOSITE_ADD);
//        notify(new NotificationEvent(NotificationTypes.RUNODECOMPOSITE_ADD, node));
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
//        notify(NotificationTypes.PRESENTATION_NEW_AUTHOR);
        notify(new NotificationEvent(NotificationTypes.PRESENTATION_NEW_AUTHOR, author));
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
//        notify(NotificationTypes.PRESENTATION_NEW_IMAGE_PATH);
        notify(new NotificationEvent(NotificationTypes.PRESENTATION_NEW_IMAGE_PATH, imagePath));
    }
}
