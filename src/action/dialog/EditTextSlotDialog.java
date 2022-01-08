package action.dialog;

import main.MainFrame;
import model.slot.Slot;
import model.slot.SlotContent;

import javax.swing.*;
import java.awt.*;

public class EditTextSlotDialog extends JDialog {
    private final JTextPane textPane = new JTextPane();
    private final Slot slot;

    public EditTextSlotDialog(Slot slot) {
        super(MainFrame.getInstance(), "Edit text", true);
        this.slot = slot;

        setSize(500, 500);
        setLocationRelativeTo(MainFrame.getInstance());
        setLayout(new BorderLayout());

        add(textPane, BorderLayout.CENTER);

        JButton saveButton = new JButton("Save");
        add(saveButton, BorderLayout.SOUTH);

        saveButton.addActionListener(e -> {
            SlotContent newContent = new SlotContent(textPane.getText());
            this.slot.setSlotContent(newContent);
            dispose();
        });

        setVisible(true);
    }
}
