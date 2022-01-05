package model;

import java.io.ObjectStreamException;
import java.io.Serial;

public class Presentation extends RuNodeComposite {

    private String author;
    private String imagePath;
    private transient Slot selectedSlot = null;

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
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
        setChanged(true);
        notify(new NotificationEvent(NotificationTypes.PRESENTATION_NEW_AUTHOR, author));
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
        setChanged(true);
        notify(new NotificationEvent(NotificationTypes.PRESENTATION_NEW_IMAGE_PATH, imagePath));
    }

    public void setSelectedSlot(Slot selectedSlot) {
        if (this.selectedSlot != null) this.selectedSlot.setSelected(false);
        this.selectedSlot = selectedSlot;
        if(this.selectedSlot != null) this.selectedSlot.setSelected(true);

        notify(new NotificationEvent(NotificationTypes.REPAINT_SLIDEVIEWS, null));
    }

    @Serial
    private Object readResolve() throws ObjectStreamException {
        initTransients();
        return this;
    }
}
