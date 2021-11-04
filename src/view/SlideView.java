package view;

import model.Slide;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SlideView extends JPanel {
    private Slide slide;
    private Image image;
    private Dimension dimension;

    public SlideView(Slide slide, Image image) {
        this.slide = slide;
        this.dimension = new Dimension(1066, 600);
        this.image = image.getScaledInstance(dimension.width, dimension.height, Image.SCALE_SMOOTH);
        init();
    }

    private void init() {
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setAlignmentX(CENTER_ALIGNMENT);

        add(new JLabel("ASD"));

        setVisible(true);
    }

    public void displaySlide(Slide slide) {
        //todo
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
//        g.drawImage(image, 0, 0, this);
    }
}
