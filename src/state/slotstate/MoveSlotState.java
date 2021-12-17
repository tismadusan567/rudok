package state.slotstate;

import model.Presentation;
import model.Slide;
import model.Slot;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MoveSlotState extends SlotState {

    private Slot current = null;
    private int offsetX = 0;
    private int offsetY = 0;

    @Override
    public void mousePressed(Slide slide, MouseEvent e, Slot slot) {
        Presentation presentation = (Presentation) slide.getParent();
        if(slot == null) {
            presentation.setSelectedSlot(null);
            return;
        }
        presentation.setSelectedSlot(slot);
        current = slot;
        offsetY = e.getPoint().y - slot.getPos().y;
        offsetX = e.getPoint().x - slot.getPos().x;
    }

    @Override
    public void mouseDragged(Slide slide, MouseEvent e, Slot slot) {
        if (current == null) return;
        current.setPos(new Point(e.getPoint().x - offsetX, e.getPoint().y - offsetY));
    }

    @Override
    public void mouseReleased(Slide slide, MouseEvent e, Slot slot) {
        current = null;
        offsetX = 0;
        offsetY = 0;
    }
}
