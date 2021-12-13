package view;

import model.NotificationEvent;
import model.Slot;
import observer.ISubscriber;

import java.awt.*;
import java.text.spi.BreakIteratorProvider;

public class SlotView implements ISubscriber {
    private Rectangle rectangle;
    private final Slot slot;

    public SlotView(Slot slot) {
        this.slot = slot;
        slot.addSubscriber(this);
        rectangle = new Rectangle(slot.getPos(), slot.getSize());
    }

    public void paint(Graphics2D g2d) {
        g2d.setPaint(slot.getColor());
        g2d.setStroke(slot.getStroke());
        g2d.draw(rectangle);
        g2d.fill(rectangle);
    }

    public boolean elementAt(Point pos) {
        return rectangle.contains(pos);
    }

    @Override
    public void update(NotificationEvent notification) {
        rectangle = new Rectangle(slot.getPos(), slot.getSize());
    }

    public Slot getSlot() {
        return slot;
    }
}
