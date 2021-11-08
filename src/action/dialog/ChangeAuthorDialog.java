package action.dialog;

import error.ErrorFactory;
import main.MainFrame;
import model.Presentation;

import javax.swing.*;
import java.awt.*;

public class ChangeAuthorDialog extends JDialog {
    private final JTextField textField;
    private final JButton button;
    private final Presentation presentation;

    public ChangeAuthorDialog(Presentation presentation) {
        super(MainFrame.getInstance(), "Change author", true);
        this.presentation = presentation;

        setSize(250, 250); //todo: add scaling
        setLocationRelativeTo(MainFrame.getInstance());
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter author name:");

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 50)); //todo: add scaling

        button = new JButton("Save author");
        initActions();

        add(label);
        add(textField);
        add(button);

        setVisible(true);

    }

    private void initActions() {
        button.addActionListener(e -> {
            String newAuthor = textField.getText();
            if (newAuthor.isBlank()) {
                ErrorFactory.getInstance().generateError(ErrorFactory.ErrorType.BLANK_RENAME);
                return;
            }
            presentation.setAuthor(newAuthor);
            dispose();
        });
    }

    public String getDialogText() {
        return textField.getText();
    }
}
