package model.slot;

import model.NotificationEvent;
import model.NotificationTypes;
import model.Slide;
import observer.IPublisher;
import observer.ISubscriber;

import java.awt.*;

import java.io.ObjectStreamException;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Slot implements IPublisher, Serializable {
    private Point pos;
    private Dimension size;
    private SerializableStrokeAdapter serializableStrokeAdapter;
    private Color color;
    private final SlotType slotType;
    private SlotContent slotContent;
    private boolean selected = false;
    private final Slide parentSlide;
    private transient List<ISubscriber> subscribers = new ArrayList<>();

    public Slot(Point pos, Stroke serializableStrokeAdapter, Color color, Slide slide, SlotType slotType) {
        this(pos, new Dimension(100, 100), serializableStrokeAdapter, color, slide, slotType);
    }

    public Slot(Point pos, Dimension size, Stroke serializableStrokeAdapter, Color color, Slide parentSlide, SlotType slotType) {
        this.pos = pos;
        this.size = size;
        this.serializableStrokeAdapter = new SerializableStrokeAdapter(serializableStrokeAdapter);
        this.color = color;
        this.parentSlide = parentSlide;
        this.slotType = slotType;
    }

    @Serial
    private Object readResolve() throws ObjectStreamException {
        subscribers = new ArrayList<>();
        return this;
    }
    
    public void setPos(Point pos) {
        this.pos = pos;
        parentSlide.setChanged(true);
        notify(new NotificationEvent(NotificationTypes.REPAINT_SLOTVIEWS, pos));
    }

    public Point getPos() {
        return pos;
    }

    public Dimension getSize() {
        return size;
    }

    public SerializableStrokeAdapter getSerializableStrokeAdapter() {
        return serializableStrokeAdapter;
    }

    public Color getColor() {
        return color;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public void setSlotContent(SlotContent slotContent) {
        this.slotContent = slotContent;
        notify(new NotificationEvent(NotificationTypes.SLOT_CHANGED_CONTENT, this));
    }

    public SlotContent getSlotContent() {
        return slotContent;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notify(NotificationEvent notification) {
        for (ISubscriber subscriber : subscribers) {
            subscriber.update(notification);
        }
    }
}
