package model;

import java.io.ObjectStreamException;
import java.io.Serial;
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
        setChanged(true);
        notify(new NotificationEvent(NotificationTypes.ADD_SLOT, slot));
    }

    public void removeSlot(Slot slot) {
        if(slot.isSelected()) {
            slot.setSelected(false);
            ((Presentation)getParent()).setSelectedSlot(null);
        }
        slots.remove(slot);
        setChanged(true);
        notify(new NotificationEvent(NotificationTypes.REMOVE_SLOT, slot));
    }

    @Serial
    private Object readResolve() throws ObjectStreamException {
        initTransients();
        return this;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public List<Slot> getSlots() {
        return slots;
    }
}
