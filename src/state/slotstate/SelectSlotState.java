package state.slotstate;

import model.Slide;
import model.Slot;

import java.awt.event.MouseEvent;

public class SelectSlotState extends SlotState{
    @Override
    public void mousePressed(Slide slide, MouseEvent e, Slot slot) {
        if(slot == null) {
            slide.setSelectedSlot(null);
            return;
        }
        slide.setSelectedSlot(slot);
    }
}
