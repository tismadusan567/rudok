package model;

import observer.IPublisher;
import observer.ISubscriber;

import java.util.*;

public abstract class RuNode implements IPublisher {
    protected String name;
    protected RuNodeComposite parent;
    private final List<ISubscriber> subscribers; //set in future

    public RuNode(String name, RuNodeComposite parent) {
        this.name = name;
        this.parent = parent;
        subscribers = new ArrayList<>();
    }

    //dont use getindexinparent when removing this from parent
    public int getIndexInParent() {
        if (parent == null) return -1;

        return parent.getChildren().indexOf(this);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        if (subscribers.contains(subscriber)) return;
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
        notify(Notifications.RUNODE_NAME_CHANGED);
    }

    public String getName() {
        return name;
    }

    public RuNodeComposite getParent() {
        return parent;
    }

    public List<ISubscriber> getSubscribers() {
        return subscribers;
    }
}
