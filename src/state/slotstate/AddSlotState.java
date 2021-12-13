package state.slotstate;

import model.Slide;
import model.Slot;

import java.awt.*;
import java.awt.event.MouseEvent;

public class AddSlotState extends SlotState{

    @Override
    public void mousePressed(Slide slide, MouseEvent e, Slot slot) {
        if (e.getButton() != MouseEvent.BUTTON1) return;

        Point pos = e.getPoint();
        Slot newSlot = new Slot(pos, slide);
        slide.addSlot(newSlot);

    }
}
