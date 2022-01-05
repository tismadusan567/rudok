package model;

import java.util.ArrayList;
import java.util.List;

public abstract class RuNodeComposite extends RuNode {

    protected final List<RuNode> children;
    private int maxChildIndex = 1;

    public RuNodeComposite(String name, RuNodeComposite parent) {
        super(name, parent);
        children = new ArrayList<>();
    }

    protected abstract void add(RuNode node);

    public void addChild(RuNode node) {
        setChanged(true);
        add(node);
        maxChildIndex++;
        notify(new NotificationEvent(NotificationTypes.RUNODECOMPOSITE_ADD, node));
    }

    public void remove(RuNode node) {
        setChanged(true);
        children.remove(node);
        notify(new NotificationEvent(NotificationTypes.RUNODECOMPOSITE_REMOVE, node));

    }

    /**
     * If a parent node is saved(changed becomes false), all it's children are also saved
     */
    @Override
    public void setChanged(boolean changed) {
        super.setChanged(changed);
        if(!changed) {
            children.forEach(child -> child.setChanged(false));
        }
    }

    public List<RuNode> getChildren() {
        return children;
    }

    public int getMaxChildIndex() {
        return maxChildIndex;
    }
}
