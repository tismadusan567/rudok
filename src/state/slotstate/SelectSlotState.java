package state.slotstate;

import model.Presentation;
import model.Slide;
import model.Slot;

import java.awt.event.MouseEvent;

public class SelectSlotState extends SlotState{
    @Override
    public void mousePressed(Slide slide, MouseEvent e, Slot slot) {
        Presentation presentation = (Presentation) slide.getParent();
        if(slot == null) {
            presentation.setSelectedSlot(null);
            return;
        }
        presentation.setSelectedSlot(slot);
    }
}
