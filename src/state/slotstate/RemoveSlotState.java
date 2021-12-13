package state.slotstate;

import model.Slide;
import model.Slot;

import java.awt.event.MouseEvent;

public class RemoveSlotState extends SlotState{
    @Override
    public void mousePressed(Slide slide, MouseEvent e, Slot slot) {
        if(slot == null) return;

        slide.removeSlot(slot);
    }
}
