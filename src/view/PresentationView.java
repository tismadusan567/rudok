package view;

import model.Presentation;
import model.RuNode;
import model.Slide;
import observer.ISubscriber;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PresentationView extends JPanel implements ISubscriber {
    private Presentation presentation;
    private int slideIndex = 0;
    private JLabel lblAuthor;
    private SlideView slideView;
    private Image image;
    private JPanel slidesPanel;

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
        this.presentation.addSubscriber(this);

        lblAuthor.setText(presentation.getAuthor());
        loadImage();

        slidesPanel.removeAll();
        slidesPanel.revalidate();

//        for(int i=0;i<10;i++) { //filler
//            slidesPanel.add(Box.createVerticalStrut(50));
//            slidesPanel.add(new SlideView(presentation.getSlideAt(slideIndex), image));
//        }
        for(RuNode child : presentation.getChildren()) {
            slidesPanel.add(Box.createVerticalStrut(50));
            slidesPanel.add(new SlideView((Slide) child, image));
        }
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
        if(notification instanceof Presentation) {
            displayPresentation((Presentation) notification);
        } else {
            System.err.println("Notification not of type Presentation in PresentationView");
        }
    }
}
