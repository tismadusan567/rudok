package view.presviewpanels;

import model.Slide;
import view.SlideView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class SlidesPanel extends JPanel {

    protected final List<SlideView> slideViews = new ArrayList<>();

    public abstract void addSlideView(Slide slide, Image image);

    public abstract void removeSlide(Slide slide);

    protected int getIndex(Slide slide) {
        int index = -1;
        for (int i = 0; i < slideViews.size(); i++) {
            var el = slideViews.get(i);
            if (el.getSlide() == slide) {
                index = i;
                break;
            }
        }
        return index;
    }

    public void changeThemeImage(Image image) {
        for (SlideView slideView : slideViews) {
            slideView.setImage(image);
            slideView.repaint();
        }
    }

    public void reset() {
        removeAll();
        revalidate();
    }
}
