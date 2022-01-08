package state.slotstate;

import model.Slide;
import model.slot.Slot;

import java.awt.event.MouseEvent;

public class SelectSlotState extends SlotState{
    @Override
    public void mousePressed(Slide slide, MouseEvent e, Slot slot) {
        selectSlot(slide, slot);
    }
}
