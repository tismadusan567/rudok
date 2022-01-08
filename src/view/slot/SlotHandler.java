package view.slot;

import model.slot.Slot;

import java.awt.*;

public abstract class SlotHandler {
    protected final Slot slot;

    public SlotHandler(Slot slot) {
        this.slot = slot;
    }

    public abstract void paintContent(Graphics2D g2d);
    public abstract void format();
}
