package model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class RuNodeComposite extends RuNode {

    protected final List<RuNode> children;
    private int maxChildIndex = 1;
    private File file = null;

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

    public List<RuNode> getChildren() {
        return children;
    }

    public int getMaxChildIndex() {
        return maxChildIndex;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
