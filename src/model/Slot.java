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
    private Paint paint;
    private final Slide parentSlide;
    private final List<ISubscriber> subscribers = new ArrayList<>();

    //default za testing
    public Slot(Point pos, Slide parentSlide) {
        this(pos, new Dimension(100, 100), new BasicStroke(2f), new Color(255, 255, 255), parentSlide);
    }

    public Slot(Point pos, Dimension size, Stroke stroke, Paint paint, Slide parentSlide) {
        this.pos = pos;
        this.size = size;
        this.stroke = stroke;
        this.paint = paint;
        this.parentSlide = parentSlide;
    }

    public Point getPos() {
        return pos;
    }

    public void setPos(Point pos) {
        this.pos = pos;
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(Dimension size) {
        this.size = size;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
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
