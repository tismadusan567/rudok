package action.dialog;

import gui.tree.MyTree;
import main.MainFrame;
import model.RuNode;

import javax.swing.*;
import java.awt.*;

public class RenameDialog extends JDialog {
    private final JTextField textField;
    private final JButton okButton;

    public RenameDialog() {
        super(MainFrame.getInstance(), "Rename", true);

        setSize(250, 250); //todo: add scaling
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 50));
        JPanel textFieldPanel = new JPanel();
        textFieldPanel.add(textField);

        okButton = new JButton("OK");

        add(new JLabel("Enter name"), BorderLayout.NORTH);
        add(textFieldPanel, BorderLayout.CENTER);
        add(okButton, BorderLayout.SOUTH);

        okButton.addActionListener(e -> {
            MyTree tree = MainFrame.getInstance().getTree();

            RuNode target = tree.getActiveNode().getRuNode();

            target.setName(textField.getText());

            SwingUtilities.updateComponentTreeUI(tree);

            dispose();
        });

        setVisible(true);
    }
}
