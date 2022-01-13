package action.dialog;

import command.RenameCommand;
import error.ErrorFactory;
import error.ErrorType;
import gui.tree.MyTree;
import main.MainFrame;
import model.RuNode;

import javax.swing.*;
import java.awt.*;

public class RenameDialog extends JDialog {
    private final JTextField textField;

    public RenameDialog() {
        super(MainFrame.getInstance(), "Rename", true);

        setSize(250, 250); //todo: add scaling
        setLocationRelativeTo(MainFrame.getInstance());
        setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 50));
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.add(textField);

        JButton okButton = new JButton("OK");

        add(new JLabel("Enter name"), BorderLayout.NORTH);
        add(textFieldPanel, BorderLayout.CENTER);
        add(okButton, BorderLayout.SOUTH);

        okButton.addActionListener(e -> {
            if(textField.getText().isBlank()) {
                ErrorFactory.getInstance().generateError(ErrorType.BLANK_RENAME);
                return;
            }

            MyTree tree = MainFrame.getInstance().getTree();

            RuNode target = tree.getActiveNode().getRuNode();

            MainFrame.getInstance().getCommandManager()
                    .addCommand(new RenameCommand(target, target.getName(), textField.getText()));

            SwingUtilities.updateComponentTreeUI(tree);

            dispose();
        });

        setVisible(true);
    }
}
