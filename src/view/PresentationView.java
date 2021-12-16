package view;

import gui.PresViewToolbar;
import gui.tree.MyTree;
import main.MainFrame;
import model.*;
import observer.ISubscriber;
import state.presstate.PresentationViewStateManager;
import state.slotstate.SlotStateManager;
import view.presviewpanels.SlidesCardPanel;
import view.presviewpanels.SlidesPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;;

public class PresentationView extends JPanel implements ISubscriber {
    private Presentation presentation;
    private Image image;

    //edit panel components
    private final JPanel editPanel = new JPanel();
    private JLabel lblAuthor;
    private final SlidesPanel slidesPanel = new SlidesPanel(1f, true);
    private final SlidesPanel thumbnailPanel = new SlidesPanel(0.1f, false);
    private final JTabbedPane jTabbedPane;

    //slideshow panel components
    private final JPanel slideShowPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final SlidesCardPanel slidesCardPanel = new SlidesCardPanel(cardLayout);

    //state managers
    private final SlotStateManager slotStateManager = new SlotStateManager();
    private final PresentationViewStateManager presentationViewStateManager = new PresentationViewStateManager();

    //color and stroke components
    private Color color = new Color(255, 255, 255);
    private Stroke stroke = new BasicStroke(2f);
    private final SpinnerNumberModel spinnerModel = new SpinnerNumberModel(2, 1, 20, 1);
    private final JSpinner jSpinner = new JSpinner(spinnerModel);

    public PresentationView(Presentation presentation, JTabbedPane jTabbedPane) {
        this.presentation = presentation;
        this.jTabbedPane = jTabbedPane;
        setLayout(new BorderLayout());

        initEditPanel();
        initSlideShowPanel();

        add(editPanel, BorderLayout.CENTER);
//        add(slideShowPanel, BorderLayout.CENTER);

        displayPresentation(presentation);

    }

    private void initSlideShowPanel() {
        slideShowPanel.setLayout(new BorderLayout());
//        slideShowPanel.setMaximumSize();
        slideShowPanel.add(slidesCardPanel, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        JButton btnPrev = new JButton("Prev");
        JButton btnNext = new JButton("Next");
        JButton btnEdit = new JButton(MainFrame.getInstance().getActionManager().getEditModeStateAction());
        buttonPanel.add(btnPrev);
        buttonPanel.add(btnNext);
        buttonPanel.add(btnEdit);
        slideShowPanel.add(buttonPanel, BorderLayout.NORTH);

        btnPrev.addActionListener(e -> {
            cardLayout.previous(slidesCardPanel);
        });

        btnNext.addActionListener(e -> {
            cardLayout.next(slidesCardPanel);
        });
    }

    private void initEditPanel() {
        editPanel.setLayout(new BorderLayout(0, 30));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        lblAuthor = new JLabel(presentation.getAuthor(), SwingConstants.CENTER);
        lblAuthor.setFont(new Font("Dialog", Font.BOLD, 20));

        ((JSpinner.DefaultEditor)jSpinner.getEditor()).getTextField().setEditable(false);
        jSpinner.addChangeListener(e -> {
            stroke = new BasicStroke(spinnerModel.getNumber().floatValue());
        });
        topPanel.add(new PresViewToolbar(jSpinner), BorderLayout.NORTH);
        topPanel.add(lblAuthor, BorderLayout.CENTER);

        editPanel.add(topPanel, BorderLayout.NORTH);

        JScrollPane slidesScrollPane = new JScrollPane(slidesPanel);
        slidesScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JScrollPane thumbnailScrollPane = new JScrollPane(thumbnailPanel);
        thumbnailScrollPane.getVerticalScrollBar().setUnitIncrement(5);

        editPanel.add(slidesScrollPane, BorderLayout.CENTER);
        editPanel.add(thumbnailScrollPane, BorderLayout.WEST);
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
        slidesCardPanel.removeAll();

        slidesPanel.revalidate();
        thumbnailPanel.revalidate();
        slidesCardPanel.revalidate();
    }

    private void addSlide(Slide slide) {
        slidesPanel.addSlideView(slide, image);
        thumbnailPanel.addSlideView(slide, image);
        slidesCardPanel.addSlideView(slide, image);
    }

    private void removeSlide(Slide slide) {
        slidesPanel.removeSlide(slide);
        thumbnailPanel.removeSlide(slide);
        slidesCardPanel.removeSlide(slide);
    }

    private void changeThemeImage() {
        slidesPanel.changeThemeImage(image);
        thumbnailPanel.changeThemeImage(image);
        slidesCardPanel.changeThemeImage(image);
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

    public void setEditMode() {
        removeAll();
        add(editPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void setSlideShowMode() {
        removeAll();
        add(slideShowPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public void startEditState() {
        presentationViewStateManager.startEditState();
        presentationViewStateManager.getCurrent().buildGUI();
    }

    public void startSlideShowState() {
        presentationViewStateManager.startSlideShowState();
        presentationViewStateManager.getCurrent().buildGUI();
    }

    public void startAddState() {
        slotStateManager.startAddState();
    }

    public void startRemoveState() {
        slotStateManager.startRemoveState();
    }

    public void startMoveState() {
        slotStateManager.startMoveState();
    }

    public void startSelectState() {
        slotStateManager.startSelectState();
    }

    public Color getColor() {
        return color;
    }

    public void chooseColor() {
        color = JColorChooser.showDialog(this, "Choose color", color);
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void mousePressed(Slide slide, MouseEvent e, Slot slot) {
        slotStateManager.getCurrent().mousePressed(slide, e, slot);
    }

    public void mouseDragged(Slide slide, MouseEvent e, Slot slot) {
        slotStateManager.getCurrent().mouseDragged(slide, e, slot);
    }

    public void mouseReleased(Slide slide, MouseEvent e, Slot slot) {
        slotStateManager.getCurrent().mouseReleased(slide, e, slot);
    }
}
