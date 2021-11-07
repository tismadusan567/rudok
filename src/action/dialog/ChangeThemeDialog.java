package action.dialog;

import main.MainFrame;
import model.Presentation;

import javax.swing.*;
import java.awt.*;

public class ChangeThemeDialog extends JDialog {
    private final Presentation presentation;
    private final JButton browseButton;
    private final JButton okButton;
    private final JLabel pathLabel;
    private String newThemePath = null;

    public ChangeThemeDialog(Presentation presentation) {
        super(MainFrame.getInstance(), "Change theme", true);
        this.presentation = presentation;

        setSize(250, 250); //todo: add scaling
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Change theme:");
        browseButton = new JButton("Browse");
        pathLabel = new JLabel();
        okButton = new JButton("OK");

        add(label);
        add(browseButton);
        add(pathLabel);
        add(okButton);

        addListeners();

        setVisible(true);
    }

    private void addListeners() {
        browseButton.addActionListener(e -> {
            SelectImageDialog selectImageDialog = new SelectImageDialog(this);
            newThemePath = selectImageDialog.showDialog();
//            System.out.println(newThemePath);
            if(newThemePath != null) pathLabel.setText(newThemePath);
        });

        okButton.addActionListener(e -> {
            if(newThemePath != null) presentation.setImagePath(newThemePath);
            dispose();
        });
    }
}
