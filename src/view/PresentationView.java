package view;

import model.Presentation;
import model.RuNode;
import model.Slide;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PresentationView extends JPanel {
    private Presentation presentation;
    private int slideIndex = 0;
    private JLabel lblAuthor;
    private SlideView slideView;
    private Image image;

    public PresentationView(Presentation presentation) {
        setLayout(new BorderLayout(0, 30));

//        author label
        lblAuthor = new JLabel(presentation.getAuthor(), SwingConstants.CENTER);
        lblAuthor.setFont(new Font("Dialog", Font.BOLD, 20));
        add(lblAuthor, BorderLayout.NORTH);

        loadImage();

//        todo: clean up code
        JPanel panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);
        for(int i=0;i<10;i++) {
            panel.add(Box.createVerticalStrut(50));
            panel.add(new SlideView(presentation.getSlideAt(slideIndex), image));
        }
        for(RuNode child : presentation.getChildren()) {
            panel.add(Box.createVerticalStrut(50));
            panel.add(new SlideView((Slide) child, image));
        }

        JScrollPane slidesScrollPane = new JScrollPane(panel);
        slidesScrollPane.getVerticalScrollBar().setUnitIncrement(16);

//        slidesScrollPane.add(slideView);
        add(slidesScrollPane, BorderLayout.CENTER);

    }

    private void loadImage() {
        URL imageURL = getClass().getResource(presentation.getImagePath());
        if(imageURL != null) {
            image = new ImageIcon(imageURL).getImage();
        } else {
            System.err.println("Image not found"); //todo: handle
        }
    }
}
