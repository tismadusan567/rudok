package view;

import model.Presentation;
import model.RuNode;
import model.RuNodeComposite;
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
        presentation.getSubscribers().removeIf(e -> e instanceof PresentationView); //presentation can only have one presentation view at any time
        presentation.addSubscriber(this);

        lblAuthor.setText(presentation.getAuthor());
        loadImage();

        slidesPanel.removeAll();
        slidesPanel.revalidate();

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
        //show different presentation
        if(notification instanceof Presentation) {
            Presentation presentation = (Presentation)notification;

            if(getParent() == null) System.err.println("Parent of presentationview is null");;

            //find index of presentation in its parent and set this components parent(ProjectView)'s title at the index to the required name
            ((ProjectView)getParent()).setTitleAt(((RuNodeComposite)presentation.getParent()).getChildren().indexOf(presentation), presentation.getName());
            displayPresentation(presentation);
        } else {
            System.err.println("Notification not of type Presentation in PresentationView");
        }
    }
}
