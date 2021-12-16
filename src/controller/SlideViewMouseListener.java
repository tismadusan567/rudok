package controller;

import main.MainFrame;
import model.Slot;
import view.SlideView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SlideViewMouseListener implements MouseListener, MouseMotionListener {
    private final SlideView slideView;

    public SlideViewMouseListener(SlideView slideView) {
        this.slideView = slideView;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Slot slot = slideView.slotAtPoint(e.getPoint());
        MainFrame.getInstance().getProjectView().getPresentationView().mousePressed(slideView.getSlide(), e, slot);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Slot slot = slideView.slotAtPoint(e.getPoint());
        MainFrame.getInstance().getProjectView().getPresentationView().mouseReleased(slideView.getSlide(), e, slot);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Slot slot = slideView.slotAtPoint(e.getPoint());
        MainFrame.getInstance().getProjectView().getPresentationView().mouseDragged(slideView.getSlide(), e, slot);
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
