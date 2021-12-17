package state.slotstate;

import model.Presentation;
import model.Slide;
import model.Slot;

import java.awt.event.MouseEvent;

public abstract class SlotState {
    public void mousePressed(Slide slide, MouseEvent e, Slot slot) { }

    public void mouseDragged(Slide slide, MouseEvent e, Slot slot) { }

    public void mouseReleased(Slide slide, MouseEvent e, Slot slot) { }

    protected void selectSlot(Slide parentSlide, Slot slot) {
        Presentation presentation = (Presentation) parentSlide.getParent();
        presentation.setSelectedSlot(slot);
    }
}
