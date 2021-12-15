package view.presviewpanels;

import model.Slide;
import view.SlideView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;



public class SlidesCardPanel extends JPanel {
    private final List<SlideView> slideViews = new ArrayList<>();


    public SlidesCardPanel(CardLayout cardLayout) {
        setLayout(cardLayout);
    }
    public void addSlideView(Slide slide, Image image) {
        SlideView newSlideView = new SlideView(slide, image, new Dimension(1066, 600), false);
        add(newSlideView, slide.getName());
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

        remove(index);

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

