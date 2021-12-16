package model;

import observer.IPublisher;
import observer.ISubscriber;

import java.awt.*;

import java.util.ArrayList;
import java.util.List;

public class Slot implements IPublisher {
    private Point pos;
    private Dimension size;
    private Stroke stroke;
    private Color color;
    private boolean selected = false;
    private final Slide parentSlide;
    private final List<ISubscriber> subscribers = new ArrayList<>();

    //default za testing
    public Slot(Point pos, Slide parentSlide) {
        this(pos, new Dimension(100, 100), new BasicStroke(2f), new Color(255, 255, 255), parentSlide);
    }

    public Slot(Point pos, Stroke stroke, Color color, Slide slide) {
        this(pos, new Dimension(100, 100), stroke, color, slide);
    }

    public Slot(Point pos, Dimension size, Stroke stroke, Color color, Slide parentSlide) {
        this.pos = pos;
        this.size = size;
        this.stroke = stroke;
        this.color = color;
        this.parentSlide = parentSlide;
    }
    
    public void setPos(Point pos) {
        this.pos = pos;
        notify(new NotificationEvent(NotificationTypes.REPAINT_SLIDEVIEWS, pos));
    }

    public Point getPos() {
        return pos;
    }

    public Dimension getSize() {
        return size;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public Color getColor() {
        return color;
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
