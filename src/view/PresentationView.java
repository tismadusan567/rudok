package view;

import gui.PresViewToolbar;
import gui.tree.MyTree;
import main.MainFrame;
import model.*;
import observer.ISubscriber;
import state.slotstate.SlotStateManager;

import javax.swing.*;
import java.awt.*;
import java.net.URL;;

public class PresentationView extends JPanel implements ISubscriber {
    private Presentation presentation;
    private final JLabel lblAuthor;
    private Image image;
    private final JPanel editPanel = new JPanel();
    private final JPanel slideShowPanel = new JPanel();
    private final SlidesPanel slidesPanel;
    private final SlidesPanel thumbnailPanel;
    private final JTabbedPane jTabbedPane;

    private final SlotStateManager slotStateManager = new SlotStateManager();

    public PresentationView(Presentation presentation, JTabbedPane jTabbedPane) {
        this.jTabbedPane = jTabbedPane;
        setLayout(new BorderLayout());
        editPanel.setLayout(new BorderLayout(0, 30));


        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(new PresViewToolbar(), BorderLayout.NORTH);
        lblAuthor = new JLabel(presentation.getAuthor(), SwingConstants.CENTER);
        lblAuthor.setFont(new Font("Dialog", Font.BOLD, 20));
        topPanel.add(lblAuthor, BorderLayout.CENTER);
        editPanel.add(topPanel, BorderLayout.NORTH);

        slidesPanel = new SlidesPanel(new Dimension(1066, 600));
        JScrollPane slidesScrollPane = new JScrollPane(slidesPanel);
        slidesScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        thumbnailPanel = new SlidesPanel(new Dimension(106, 60));
        JScrollPane thumbnailScrollPane = new JScrollPane(thumbnailPanel);
        thumbnailScrollPane.getVerticalScrollBar().setUnitIncrement(5);

        editPanel.add(slidesScrollPane, BorderLayout.CENTER);
        editPanel.add(thumbnailScrollPane, BorderLayout.WEST);

        add(editPanel, BorderLayout.CENTER);

        displayPresentation(presentation);


    }

    private void displayPresentation(Presentation presentation) {
        this.presentation = presentation;
        presentation.getSubscribers().removeIf(e -> e instanceof PresentationView); //presentation can only have one presentation view at any time
        presentation.addSubscriber(this);

        lblAuthor.setText(presentation.getAuthor());
        loadImage();
        resetSlidePanels();
        for (RuNode child : presentation.getChildren()) {
            addSlide((Slide) child);
        }
    }

    private void resetSlidePanels() {
        slidesPanel.removeAll();
        thumbnailPanel.removeAll();

        slidesPanel.revalidate();
        thumbnailPanel.revalidate();
    }

    private void addSlide(Slide slide) {
        slidesPanel.addSlideView(slide, image);
        thumbnailPanel.addSlideView(slide, image);
    }

    private void removeSlide(Slide slide) {
        slidesPanel.removeSlide(slide);
        thumbnailPanel.removeSlide(slide);
    }

    private void changeThemeImage() {
        slidesPanel.changeThemeImage(image);
        thumbnailPanel.changeThemeImage(image);
    }

    private void loadImage() {
        URL imageURL = getClass().getResource(presentation.getImagePath());
        if (imageURL != null) {
            image = new ImageIcon(imageURL).getImage();
        } else {
            System.err.println("Image not found"); //todo: handle
        }
    }

    @Override
    public void update(NotificationEvent notification) {
        //change presentation
//        if (notification instanceof Presentation presentation) {
//            displayPresentation(presentation);
//        }

        //remove slide
        if (notification.getType() == NotificationTypes.RUNODECOMPOSITE_REMOVE) {
            Slide slide = (Slide) notification.getMessage();
            removeSlide(slide);

            //select presentation
            MyTree tree = MainFrame.getInstance().getTree();
            tree.selectNode(tree.getActivePresentationNode());
        }

        //add slide
        if (notification.getType() == NotificationTypes.RUNODECOMPOSITE_ADD) {
            //add newest element from children
//            addSlide((Slide) presentation.getChildren().get(presentation.getChildren().size() - 1));

            addSlide((Slide) notification.getMessage());
            validate();
        }

        //change name
        if (notification.getType() == NotificationTypes.RUNODE_NAME_CHANGED) {
            //find index of presentation in its parent and set this components parent(ProjectView)'s title at that index to the required name
//            ((JTabbedPane) getParent()).setTitleAt(presentation.getIndexInParent(), presentation.getName());
            jTabbedPane.setTitleAt(presentation.getIndexInParent(), presentation.getName());
        }

        //change author name
        if (notification.getType() == NotificationTypes.PRESENTATION_NEW_AUTHOR) {
            lblAuthor.setText(presentation.getAuthor());
        }

        //change theme image
        if (notification.getType() == NotificationTypes.PRESENTATION_NEW_IMAGE_PATH) {
            loadImage();
            changeThemeImage();
        }
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public SlotStateManager getSlotStateManager() {
        return slotStateManager;
    }
}
