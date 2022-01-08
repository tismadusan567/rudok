package view;

import controller.SlideViewMouseListener;
import model.*;
import model.slot.Slot;
import observer.ISubscriber;
import view.slot.SlotView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SlideView extends JPanel implements ISubscriber {
    private Slide slide;
    private Image image;
    private final boolean onSlideShow;
    private final JLabel nameLabel;
    private final Dimension dimension;
    private final List<SlotView> slotViews = new ArrayList<>();
    private final float scale;

    public SlideView(Slide slide, Image image, float scale, boolean onSlideShow) {
        this.scale = scale;
        this.dimension = new Dimension((int) (1066 * scale), (int) (600 * scale));
        this.image = image.getScaledInstance(dimension.width, dimension.height, Image.SCALE_SMOOTH);
        this.onSlideShow = onSlideShow;
        nameLabel = new JLabel();
        add(nameLabel);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setAlignmentX(CENTER_ALIGNMENT);
        displaySlide(slide);

        SlideViewMouseListener listener = new SlideViewMouseListener(this);
        if (!onSlideShow) {
            addMouseListener(listener);
            addMouseMotionListener(listener);
        }
    }

    private void addSlotView(Slot slot) {
        slotViews.add(new SlotView(slot, scale, onSlideShow));
        slot.addSubscriber(this);
    }

    private void displaySlide(Slide slide) {
        this.slide = slide;
        this.slide.addSubscriber(this);

        nameLabel.setText(slide.getName());

        for (Slot slot : slide.getSlots()) {
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
    }

    @Override
    public void update(NotificationEvent notification) {
        switch (notification.getType()) {
            case RUNODE_NAME_CHANGED -> nameLabel.setText(slide.getName());
            case ADD_SLOT -> {
                addSlotView((Slot) notification.getMessage());
                repaint();
            }
            case REMOVE_SLOT -> {
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
            case REPAINT_SLOTVIEWS -> repaint();
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
