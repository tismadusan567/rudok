package state.slotstate;

import main.MainFrame;
import model.Slide;
import model.slot.Slot;
import view.PresentationView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class AddSlotState extends SlotState {

    @Override
    public void mousePressed(Slide slide, MouseEvent e, Slot slot) {
        if (e.getButton() != MouseEvent.BUTTON1) return;

        Point pos = e.getPoint();
        PresentationView pw = MainFrame.getInstance().getProjectView().getPresentationView();
        Color color = pw.getColor();
        Stroke stroke;
        if (pw.isStrokeDashed()) {
            stroke = new BasicStroke(
                    pw.getStrokeWidth(),
                    BasicStroke.CAP_SQUARE, BasicStroke.JOIN_MITER,
                    10.0f,
                    new float[]{10f, 25f},
                    0.0f
            );
        } else {
            stroke = new BasicStroke(pw.getStrokeWidth());
        }
        Slot newSlot = new Slot(pos, stroke, color, slide, pw.getSlotType());
        slide.addSlot(newSlot);
        selectSlot(slide, newSlot);
    }
}
