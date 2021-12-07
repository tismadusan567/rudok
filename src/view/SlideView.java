package view;

import model.Notifications;
import model.Slide;
import observer.ISubscriber;

import javax.swing.*;
import java.awt.*;

public class SlideView extends JPanel implements ISubscriber {
    private Slide slide;
    private Image image;
    private final JLabel nameLabel;
    private final Dimension dimension;

    public SlideView(Slide slide, Image image, Dimension dimension) {
//        this.dimension = new Dimension(1066, 600);
        this.dimension = dimension;
        this.image = image.getScaledInstance(dimension.width, dimension.height, Image.SCALE_SMOOTH);
        nameLabel = new JLabel();
        add(nameLabel);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setAlignmentX(CENTER_ALIGNMENT);
        displaySlide(slide);
    }

    private void displaySlide(Slide slide) {
        this.slide = slide;
        slide.getSubscribers().removeIf(e -> e instanceof SlideView);
        this.slide.addSubscriber(this);

        nameLabel.setText(slide.getName());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
//        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void update(Object notification) {
        //change slide
        if (notification instanceof Slide newSlide) {
            displaySlide(newSlide);
        }

        //change name
        if (notification == Notifications.RUNODE_NAME_CHANGED) {
            nameLabel.setText(slide.getName());
        }

    }

    public void setImage(Image image) {
        this.image = image.getScaledInstance(dimension.width, dimension.height, Image.SCALE_SMOOTH);
    }

    public Slide getSlide() {
        return slide;
    }
}
