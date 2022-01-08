package view.slot;

import model.NotificationEvent;
import model.slot.Slot;
import observer.ISubscriber;

import java.awt.*;

public class SlotView implements ISubscriber {
    private final boolean onSlideShow;
    private Rectangle rectangle;
    private final Slot slot;
    private final float scale;
    private final SlotHandler slotHandler;

    public SlotView(Slot slot, float scale, boolean onSlideShow) {
        this.slot = slot;
        this.scale = scale;
        this.onSlideShow = onSlideShow;
        slot.addSubscriber(this);


        slotHandler = switch (slot.getSlotType()) {
            case TEXT -> new TextSlotHandler(slot);
            case IMAGE -> new ImageSlotHandler(slot);
        };
        slotHandler.format();
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
        if(onSlideShow) slotHandler.paintContent(g2d);
    }

    public boolean elementAt(Point pos) {
        return rectangle.contains(pos);
    }

    @Override
    public void update(NotificationEvent notification) {
        switch (notification.getType()) {
            case REPAINT_SLOTVIEWS -> updateRectangle();
            case SLOT_CHANGED_CONTENT -> slotHandler.format();
        }
    }

    public Slot getSlot() {
        return slot;
    }
}
