package action.dialog;

import main.MainFrame;

import javax.swing.*;
import java.awt.*;

public class OnStartDialog extends JDialog {

    public OnStartDialog() {
        super(MainFrame.getInstance(), "Open existing workspace", true);
        setSize(250, 250);
        setLocationRelativeTo(MainFrame.getInstance());
        setLayout(new BorderLayout());
        JLabel lbl = new JLabel("Would you like to open an existing workspace?");
        lbl.setFont(lbl.getFont().deriveFont(15.0f));
        add(lbl, BorderLayout.CENTER);
        JButton btnYes = new JButton("Yes");
        JButton btnNo = new JButton("No");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnYes);
        buttonPanel.add(btnNo);
        add(buttonPanel, BorderLayout.SOUTH);
        btnYes.addActionListener(e -> {
            MainFrame.getInstance().getActionManager().getOpenWorkspaceAction().actionPerformed(e);
            dispose();
        });
        btnNo.addActionListener(e -> dispose());
        setVisible(true);
    }
}
