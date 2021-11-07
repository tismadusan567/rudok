package view;

import model.*;
import observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PresentationView extends JPanel implements ISubscriber {
    private Presentation presentation;
    private int slideIndex = 0;
    private final JLabel lblAuthor;
    private Image image;
    private final JPanel slidesPanel;
    private final List<SlideView> slideViews = new ArrayList<>();

    public PresentationView(Presentation presentation) {

        setLayout(new BorderLayout(0, 30));

        lblAuthor = new JLabel(presentation.getAuthor(), SwingConstants.CENTER);
        lblAuthor.setFont(new Font("Dialog", Font.BOLD, 20));
        add(lblAuthor, BorderLayout.NORTH);

        slidesPanel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(slidesPanel, BoxLayout.Y_AXIS);
        slidesPanel.setLayout(boxLayout);

        JScrollPane slidesScrollPane = new JScrollPane(slidesPanel);
        slidesScrollPane.getVerticalScrollBar().setUnitIncrement(16);
        add(slidesScrollPane, BorderLayout.CENTER);

        displayPresentation(presentation);

    }

    private void displayPresentation(Presentation presentation) {
        this.presentation = presentation;
        presentation.getSubscribers().removeIf(e -> e instanceof PresentationView); //presentation can only have one presentation view at any time
        presentation.addSubscriber(this);

        lblAuthor.setText(presentation.getAuthor());
        loadImage();

        slidesPanel.removeAll();
        slidesPanel.revalidate();

        for(RuNode child : presentation.getChildren()) {
            addSlideView((Slide) child);
        }
    }

    private void addSlideView(Slide slide) {
        slidesPanel.add(Box.createVerticalStrut(50));
        SlideView newSlideView = new SlideView(slide, image);
        slidesPanel.add(newSlideView);
        slideViews.add(newSlideView);
    }

    private void loadImage() {
        URL imageURL = getClass().getResource(presentation.getImagePath());
        if(imageURL != null) {
            image = new ImageIcon(imageURL).getImage();
        } else {
            System.err.println("Image not found"); //todo: handle
        }
    }

    @Override
    public void update(Object notification) {
        //change presentation
        if(notification instanceof Presentation presentation) {
            displayPresentation(presentation);
        }

        //add new slide
        if(notification instanceof Slide slide) {
            addSlideView(slide);
            slidesPanel.getParent().validate();
        }

        //change name
        if(notification == Notifications.RUNODE_NAME_CHANGED) {
            //find index of presentation in its parent and set this components parent(ProjectView)'s title at the index to the required name
            ((JTabbedPane)getParent()).setTitleAt(presentation.getIndexInParent(), presentation.getName());
        }

        //change author name
        if(notification == Notifications.PRESENTATION_NEW_AUTHOR) {
            lblAuthor.setText(presentation.getAuthor());
        }

        //change theme image
        if(notification == Notifications.PRESENTATION_NEW_IMAGE_PATH) {
            loadImage();
            for(SlideView slideView : slideViews) {
                slideView.setImage(image);
                slideView.repaint();
            }
        }
    }
}
