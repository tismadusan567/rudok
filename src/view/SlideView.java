package view;

import controller.SlideViewMouseListener;
import model.*;
import observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SlideView extends JPanel implements ISubscriber {
    private Slide slide;
    private Image image;
    private final JLabel nameLabel;
    private final Dimension dimension;
    private final List<SlotView> slotViews = new ArrayList<>();

    public SlideView(Slide slide, Image image, Dimension dimension, boolean hasListeners) {
//        this.dimension = new Dimension(1066, 600);
        this.dimension = dimension;
        this.image = image.getScaledInstance(dimension.width, dimension.height, Image.SCALE_SMOOTH);
        nameLabel = new JLabel();
        add(nameLabel);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setAlignmentX(CENTER_ALIGNMENT);
        displaySlide(slide);
        
        SlideViewMouseListener listener = new SlideViewMouseListener(this);
        if(hasListeners) {
            addMouseListener(listener);
            addMouseMotionListener(listener);
        }
    }

    private void addSlotView(Slot slot) {
        slotViews.add(new SlotView(slot));
        slot.addSubscriber(this);
    }

    private void displaySlide(Slide slide) {
        this.slide = slide;
//        slide.getSubscribers().removeIf(e -> e instanceof SlideView);
        this.slide.addSubscriber(this);

        nameLabel.setText(slide.getName());

        for (Slot slot : slide.getSlots()) {
//            slotViews.add(new SlotView(slot));
            addSlotView(slot);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        for (SlotView sv : slotViews) {
            sv.paint((Graphics2D) g);
        }
//        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void update(NotificationEvent notification) {

        //change name
        if (notification.getType() == NotificationTypes.RUNODE_NAME_CHANGED) {
            nameLabel.setText(slide.getName());
        }

        if (notification.getType() == NotificationTypes.ADD_SLOT) {
//            slotViews.add(new SlotView((Slot) notification.getMessage()));
            addSlotView((Slot) notification.getMessage());
            repaint();
        }

        if (notification.getType() == NotificationTypes.REMOVE_SLOT) {
            Slot slot = (Slot) notification.getMessage();
            int index = -1;
            for (int i = 0; i < slotViews.size(); i++) {
                //find the slotview with this slot
                if (slotViews.get(i).getSlot() == slot) {
                    index = i;
                    break;
                }
            }
            slotViews.get(index).getSlot().removeSubscriber(slotViews.get(index));
            slotViews.remove(index);
            repaint();
        }

        if (notification.getType() == NotificationTypes.REPAINT_SLIDEVIEWS) {
            repaint();
        }

    }

    public void setImage(Image image) {
        this.image = image.getScaledInstance(dimension.width, dimension.height, Image.SCALE_SMOOTH);
    }

    public Slide getSlide() {
        return slide;
    }

    public Slot slotAtPoint(Point point) {
        for (int i = slotViews.size() - 1; i >= 0; i--) {
            if (slotViews.get(i).elementAt(point)) {
                return slotViews.get(i).getSlot();
            }
        }
        return null;
    }
}
