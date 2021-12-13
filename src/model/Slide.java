package model;

import java.util.ArrayList;
import java.util.List;

public class Slide extends RuNode {
    private int redniBroj;
    private final List<Slot> slots = new ArrayList<>();

    public Slide(String name, RuNodeComposite parent, int redniBroj) {
        super(name, parent);
        this.redniBroj = redniBroj;
    }

    public void addSlot(Slot slot) {
        slots.add(slot);
        notify(new NotificationEvent(NotificationTypes.ADD_SLOT, slot));
    }

    public void removeSlot(Slot slot) {
        slots.remove(slot);
        notify(new NotificationEvent(NotificationTypes.REMOVE_SLOT, slot));
    }

    public void repaintViews() {
        notify(new NotificationEvent(NotificationTypes.REPAINT_SLIDEVIEWS, null));
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public List<Slot> getSlots() {
        return slots;
    }

    //ne zaboravi notify kad budes dodavo funkcionalnost
}
