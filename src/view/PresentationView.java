package view;

import model.Presentation;

import javax.swing.*;
import java.awt.*;

public class PresentationView extends JPanel {
    private Presentation presentation;
    private int slideIndex = 0;
    private JLabel lblAuthor;
    private SlideView slideView;

    public PresentationView(Presentation presentation) {
        setLayout(new BorderLayout(0, 30));
        lblAuthor = new JLabel(presentation.getAuthor(), SwingConstants.CENTER);
        lblAuthor.setFont(new Font("Dialog", Font.BOLD, 20));
        add(lblAuthor, BorderLayout.NORTH);

//        Image image = new ImageIcon(presentation.getImagePath()).getImage();
        Image image = new ImageIcon(getClass().getResource(presentation.getImagePath())).getImage(); //todo: check null
        slideView = new SlideView(presentation.getSlideAt(slideIndex), image);
        add(slideView, BorderLayout.CENTER);

    }
}
