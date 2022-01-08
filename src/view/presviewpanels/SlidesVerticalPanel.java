package view.presviewpanels;

import model.Slide;
import view.SlideView;

import javax.swing.*;
import java.awt.*;

public class SlidesVerticalPanel extends SlidesPanel {
    private final float scale;

    public SlidesVerticalPanel(float scale) {
        this.scale = scale;
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
    }

    @Override
    public void addSlideView(Slide slide, Image image) {
        add(Box.createVerticalStrut((int) (scale * 600 / 10)));
        SlideView newSlideView = new SlideView(slide, image, scale, false);
        add(newSlideView);
        slideViews.add(newSlideView);
    }

    @Override
    public void removeSlide(Slide slide) {
        int index = getIndex(slide);
        if (index == -1) return; //removing nonexistent slide, should never happen

        remove(2 * index); //2*i because of vertical struts
        remove(2 * index);

        validate();
        repaint();
        slideViews.remove(index);
    }
}
