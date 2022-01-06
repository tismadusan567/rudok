package model;

import observer.IPublisher;
import observer.ISubscriber;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class RuNode implements IPublisher, Serializable {
    protected String name;
    protected RuNodeComposite parent;
    private transient List<ISubscriber> subscribers = new ArrayList<>();
    private transient boolean changed = true;

    public RuNode(String name, RuNodeComposite parent) {
        this.name = name;
        this.parent = parent;
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
    public void notify(NotificationEvent notification) {
        for (ISubscriber subscriber : subscribers) {
            subscriber.update(notification);
        }
    }

    /**
     * Used for serialization of subclasses
     */
    protected void initTransients() {
        subscribers = new ArrayList<>();
        changed = false;
    }

    public void setName(String name) {
        this.name = name;
        setChanged(true);
        notify(new NotificationEvent(NotificationTypes.RUNODE_NAME_CHANGED, name));

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


    /**
     * Sets this runodes changed value and if changed, all its predecessors to changed
     */
    public void setChanged(boolean changed) {
        this.changed = changed;
        if (changed && parent != null) parent.setChanged(true);
    }

    public boolean isChanged() {
        return changed;
    }

    public void setParent(RuNodeComposite parent) {
        this.parent = parent;
    }
}
