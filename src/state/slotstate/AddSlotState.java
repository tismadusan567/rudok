package state.slotstate;

import main.MainFrame;
import model.Slide;
import model.Slot;
import view.PresentationView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class AddSlotState extends SlotState{

    @Override
    public void mousePressed(Slide slide, MouseEvent e, Slot slot) {
        if (e.getButton() != MouseEvent.BUTTON1) return;

        Point pos = e.getPoint();
//        Slot newSlot = new Slot(pos, slide);
        PresentationView pw = MainFrame.getInstance().getProjectView().getPresentationView();
        Color color = pw.getColor();
        Stroke stroke = pw.getStroke();
        Slot newSlot = new Slot(pos, stroke, color, slide);
        slide.addSlot(newSlot);

    }
}
