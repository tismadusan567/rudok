package model;

import model.slot.Slot;

import java.io.ObjectStreamException;
import java.io.Serial;

public class Presentation extends RuNodeComposite {

    private String author;
    private String imagePath;
    private Slot selectedSlot = null;
    private boolean shared = false;

    public Presentation(String name, RuNodeComposite parent, String author, String imagePath) {
        super(name, parent);
        this.author = author;
        this.imagePath = imagePath;
    }

    @Override
    protected void add(RuNode node) {
        if (node instanceof Slide) children.add(node);
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

        notify(new NotificationEvent(NotificationTypes.REPAINT_SLOTVIEWS, null));
    }

    public Slot getSelectedSlot() {
        return selectedSlot;
    }

    @Serial
    private Object readResolve() throws ObjectStreamException {
        initTransients();
        return this;
    }

    /**
     * If a presentation is saved(changed becomes false), all it's slides are also saved
     */
    @Override
    public void setChanged(boolean changed) {
        super.setChanged(changed);
        if(!changed) {
            children.forEach(child -> child.setChanged(false));
        }
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }
}
