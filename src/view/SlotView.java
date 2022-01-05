package view;

import model.NotificationEvent;
import model.Slot;
import observer.ISubscriber;

import java.awt.*;

public class SlotView implements ISubscriber {
    private Rectangle rectangle;
    private final Slot slot;
    private final float scale;

    public SlotView(Slot slot, float scale) {
        this.slot = slot;
        this.scale = scale;
        slot.addSubscriber(this);
        updateRectangle();
    }

    private void updateRectangle() {
        Point pos = new Point((int) (slot.getPos().x * scale), (int) (slot.getPos().y * scale));
        Dimension size = new Dimension((int) (slot.getSize().width * scale), (int) (slot.getSize().height * scale));
        rectangle = new Rectangle(pos, size);
    }

    public void paint(Graphics2D g2d) {
        BasicStroke b = (BasicStroke) slot.getSerializableStrokeAdapter().getStroke();
        if (slot.isSelected()) {
            g2d.setPaint(Color.ORANGE);
            g2d.setStroke(new BasicStroke((b.getLineWidth() * scale + 8f)));

        } else {
            g2d.setPaint(Color.BLACK);
            g2d.setStroke(new BasicStroke(
                    b.getLineWidth() * scale,
                    BasicStroke.CAP_SQUARE,
                    BasicStroke.JOIN_MITER,
                    10.0f,
                    b.getDashArray(),
                    0.0f
            ));
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
        updateRectangle();
    }

    public Slot getSlot() {
        return slot;
    }
}
