package view.presviewpanels;

import model.Slide;
import view.SlideView;

import java.awt.*;




public class SlidesCardPanel extends SlidesPanel {
    public SlidesCardPanel(CardLayout cardLayout) {
        setLayout(cardLayout);
    }

    @Override
    public void addSlideView(Slide slide, Image image) {
        SlideView newSlideView = new SlideView(slide, image, 1f, false);
        add(newSlideView, slide.getName());
        slideViews.add(newSlideView);
    }

    @Override
    public void removeSlide(Slide slide) {
        int index = getIndex(slide);
        if (index == -1) return; //removing nonexistent slide, should never happen

        remove(index);

        validate();
        repaint();
        slideViews.remove(index);
    }
}

