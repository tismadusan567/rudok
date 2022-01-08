package view.slot;

import model.slot.Slot;
import model.slot.SlotContent;

import java.awt.*;

public class TextSlotHandler extends SlotHandler {
    public TextSlotHandler(Slot slot) {
        super(slot);
    }

    @Override
    public void paintContent(Graphics2D g2d) {
        if(slot.getSlotContent() == null) return;
        g2d.setPaint(Color.BLACK);
        g2d.drawString(slot.getSlotContent().getContentString(), slot.getPos().x, slot.getPos().y);
    }

    @Override
    public void format() {
        SlotContent slotContent = slot.getSlotContent();
        if(slotContent == null) return;
        //format
    }
}
