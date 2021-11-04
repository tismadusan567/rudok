package view;

import model.Presentation;
import model.Project;
import model.Slide;
import observer.ISubscriber;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SlideView extends JPanel implements ISubscriber {
    private Slide slide;
    private Image image;
    private Dimension dimension;

    public SlideView(Slide slide, Image image) {
        this.dimension = new Dimension(1066, 600);
        this.image = image.getScaledInstance(dimension.width, dimension.height, Image.SCALE_SMOOTH);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setAlignmentX(CENTER_ALIGNMENT);
        displaySlide(slide);
    }

    private void displaySlide(Slide slide) {
        this.slide = slide;
        this.slide.addSubscriber(this);

        add(new JLabel("ASD"));

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
//        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void update(Object notification) {
        if(notification instanceof Slide) {
            displaySlide((Slide) notification);
        } else {
            System.err.println("Notification not of type Slide in SlideView");
        }
    }
}
