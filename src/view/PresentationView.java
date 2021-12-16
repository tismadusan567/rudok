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
import view.presviewpanels.SlidesVerticalPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PresentationView extends JPanel implements ISubscriber {
    private Presentation presentation;
    private Image image;

    //edit panel components
    private final JPanel editPanel = new JPanel();
    private JLabel lblAuthor;
    private final SlidesVerticalPanel slidesVerticalPanel = new SlidesVerticalPanel(1f, true);
    private final SlidesVerticalPanel thumbnailPanel = new SlidesVerticalPanel(0.1f, false);
    private final JTabbedPane jTabbedPane;

    //slideshow panel components
    private final JPanel slideShowPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final SlidesCardPanel slidesCardPanel = new SlidesCardPanel(cardLayout);

    private final List<SlidesPanel> slidesPanels = new ArrayList<>();

    //state managers
    private final SlotStateManager slotStateManager = new SlotStateManager();
    private final PresentationViewStateManager presentationViewStateManager = new PresentationViewStateManager();

    //color and stroke components
    private Color color = new Color(255, 255, 255);
//    private Stroke stroke = new BasicStroke(2f);
    private float strokeWidth = 2f;
    private boolean isStrokeDashed = false;
    private final SpinnerNumberModel spinnerModel = new SpinnerNumberModel(2, 1, 20, 1);
    private final JSpinner jSpinner = new JSpinner(spinnerModel);
    private final JCheckBox cbDashedStroke = new JCheckBox("Dashed stroke");

    public PresentationView(Presentation presentation, JTabbedPane jTabbedPane) {
        this.presentation = presentation;
        this.jTabbedPane = jTabbedPane;
        setLayout(new BorderLayout());

        //components that contain and manage slides
        slidesPanels.add(slidesVerticalPanel);
        slidesPanels.add(thumbnailPanel);
        slidesPanels.add(slidesCardPanel);

        initEditPanel();
        initSlideShowPanel();

        add(editPanel, BorderLayout.CENTER);

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
            strokeWidth = spinnerModel.getNumber().floatValue();
        });

        cbDashedStroke.addChangeListener(e -> {
            isStrokeDashed = cbDashedStroke.isSelected();
        });

        topPanel.add(new PresViewToolbar(jSpinner, cbDashedStroke), BorderLayout.NORTH);
        topPanel.add(lblAuthor, BorderLayout.CENTER);

        editPanel.add(topPanel, BorderLayout.NORTH);

        JScrollPane slidesScrollPane = new JScrollPane(slidesVerticalPanel);
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
        slidesPanels.forEach(SlidesPanel::reset);

    }

    private void addSlide(Slide slide) {
        slidesPanels.forEach(slidesPanel -> slidesPanel.addSlideView(slide, image));

    }

    private void removeSlide(Slide slide) {
        slidesPanels.forEach(slidesPanel -> slidesPanel.removeSlide(slide));

    }

    private void changeThemeImage() {
        slidesPanels.forEach(slidesPanel -> slidesPanel.changeThemeImage(image));
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

    public float getStrokeWidth() {
        return strokeWidth;
    }

    public boolean isStrokeDashed() {
        return isStrokeDashed;
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
