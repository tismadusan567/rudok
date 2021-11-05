package actions;

import main.MainFrame;

import javax.swing.*;
import java.awt.*;

public class ChangeAuthorDialog extends JDialog {
    private final JTextField textField;
    private final JButton button;

    public ChangeAuthorDialog() {
        super(MainFrame.getInstance(), "Change author", true);

        setSize(250, 250); //todo: add scaling
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        JLabel label = new JLabel("Enter new author name:");
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 50)); //todo: add scaling
        button = new JButton("Change author");

        add(label);
        add(textField);
        add(button);

        setVisible(true);

    }

    public String getDialogText() {
        return textField.getText();
    }
}
