package view;

import model.Slide;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SlidesPanel extends JPanel {
    private final Dimension dimension;
    private final List<SlideView> slideViews = new ArrayList<>();

    public SlidesPanel(Dimension dimension) {
        this.dimension = dimension;
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(boxLayout);
    }

    public void addSlideView(Slide slide, Image image) {
        add(Box.createVerticalStrut(dimension.height / 10));
        SlideView newSlideView = new SlideView(slide, image, dimension);
        add(newSlideView);
        slideViews.add(newSlideView);
    }

    public void removeSlide(Slide slide) {
        int index = -1;
        for (int i = 0; i < slideViews.size(); i++) {
            var el = slideViews.get(i);
            if (el.getSlide() == slide) {
                index = i;
                break;
            }
        }
        if (index == -1) return; //removing nonexistent slide, should never happen

        remove(2 * index); //2*i because of vertical struts
        remove(2 * index);

        validate();
        repaint();
        slideViews.remove(index);
    }

    public void changeThemeImage(Image image) {
        for (SlideView slideView : slideViews) {
            slideView.setImage(image);
            slideView.repaint();
        }
    }
}
