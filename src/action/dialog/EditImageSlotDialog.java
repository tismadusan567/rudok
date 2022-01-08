package action.dialog;

import action.filefilter.ImageFileFilter;
import main.MainFrame;
import model.slot.Slot;
import model.slot.SlotContent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class EditImageSlotDialog extends JDialog {
    private final Slot slot;
    private String filePath = null;
    private final JLabel imageLabel = new JLabel("", SwingConstants.CENTER);
    private final JLabel pathLabel = new JLabel("", SwingConstants.CENTER);

    public EditImageSlotDialog(Slot slot) {
        super(MainFrame.getInstance(), "Select image", true);
        this.slot = slot;

        setSize(500, 500);
        setLocationRelativeTo(MainFrame.getInstance());
        setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton btnSelect = new JButton("Select image");
        JButton btnSave = new JButton("Save");
        buttonPanel.add(btnSelect);
        buttonPanel.add(btnSave);
        add(buttonPanel, BorderLayout.SOUTH);

        add(imageLabel, BorderLayout.CENTER);
        add(pathLabel, BorderLayout.NORTH);

        btnSelect.addActionListener(e -> {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileFilter(new ImageFileFilter());
            if (jfc.showOpenDialog(MainFrame.getInstance()) != JFileChooser.APPROVE_OPTION) return;

            try {
                Image image = new ImageIcon(ImageIO.read(jfc.getSelectedFile())).getImage().getScaledInstance(450, 350, Image.SCALE_SMOOTH);
                this.imageLabel.setIcon(new ImageIcon(image));
                filePath = jfc.getSelectedFile().getAbsolutePath();
                pathLabel.setText(filePath);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        });

        btnSave.addActionListener(e -> {
            SlotContent newContent = new SlotContent(filePath);
            this.slot.setSlotContent(newContent);
            dispose();
        });

        setVisible(true);
    }
}

