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
        if(slot.isSelected()) {
            g2d.setPaint(Color.ORANGE);
            g2d.setStroke(new BasicStroke(((BasicStroke) slot.getStroke()).getLineWidth() + 8f));

        } else {
            g2d.setPaint(Color.BLACK);
            g2d.setStroke(slot.getStroke());

        }
        g2d.draw(rectangle);
        g2d.setPaint(slot.getColor());
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
