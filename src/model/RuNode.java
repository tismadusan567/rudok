package model;

import observer.IPublisher;
import observer.ISubscriber;

import java.util.*;

public abstract class RuNode implements IPublisher {
    protected String name;
    protected RuNode parent;
    private final List<ISubscriber> subscribers; //set in future

    public RuNode(String name, RuNode parent) {
        this.name = name;
        this.parent = parent;
        subscribers = new ArrayList<>();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        if(subscribers.contains(subscriber)) return;
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notify(Object notification) {
        for (ISubscriber subscriber : subscribers) {
            subscriber.update(notification);
        }
    }

    public void setName(String name) {
        this.name = name;
        notify(this);
    }

    public String getName() {
        return name;
    }

    public RuNode getParent() {
        return parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RuNode ruNode = (RuNode) o;
        return Objects.equals(name, ruNode.name) && Objects.equals(parent, ruNode.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parent);
    }
}
