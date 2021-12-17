package action.dialog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SelectImageDialog extends JDialog {
    private String selectedImage = null;
    private final String imageDirectory = "/backgrounds";
    private final List<JRadioButton> jRadioButtonList = new ArrayList<>();

    public SelectImageDialog(JDialog parent) {
        super(parent, "Select image", true);
        init();
    }

    public SelectImageDialog(JFrame parent) {
        super(parent, "Select image", true);
        init();
    }

    private void init() {
        setSize(250, 250); //todo: add scaling
        setLocationRelativeTo(getParent());
        setLayout(new BorderLayout());

        JLabel label = new JLabel("Select image");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(new EmptyBorder(5, 5, 15, 5));

        add(label, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        ButtonGroup buttonGroup = new ButtonGroup();

        String imageDirectoryPath = "./src/res" + imageDirectory;
        File dir = new File(imageDirectoryPath);
        File[] directoryListing = dir.listFiles();
        if (directoryListing != null) {
            for (File child : directoryListing) {
//                System.out.println(child.getName());
                JRadioButton radioButton = new JRadioButton(child.getName());
                radioButton.setMinimumSize(new Dimension(50, 200));
                buttonGroup.add(radioButton);
                buttonsPanel.add(radioButton);
                jRadioButtonList.add(radioButton);
            }
        }

        JScrollPane scrollPane = new JScrollPane(buttonsPanel);
        scrollPane.getVerticalScrollBar().setUnitIncrement(6);

        add(scrollPane, BorderLayout.CENTER);

        JButton button = new JButton("Select");
        button.addActionListener(e -> {
            for (var el : jRadioButtonList) {
                if (el.isSelected()) {
                    selectedImage = el.getText();
                    break;
                }
            }
            setVisible(false);
            dispose();
        });
        add(button, BorderLayout.SOUTH);
    }

    String showDialog() {
        setVisible(true);
        if (selectedImage == null) return null;
        return imageDirectory + "/" + selectedImage;
    }
}
