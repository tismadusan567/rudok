package temp;

import model.Presentation;
import model.RuNode;
import model.Slide;

import javax.swing.*;
import java.awt.*;

public class SlidePanel extends JPanel {
    private Slide slide;
    private Image image;

    public SlidePanel(Slide slide, Image image) {
        this.slide = slide;
        this.image = image;
        init();
    }

    private void init() {
        add(new JLabel("ASD"));
        setVisible(true);
    }

    public void displaySlide(Slide slide) {
        this.slide = slide;
        //todo
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}
