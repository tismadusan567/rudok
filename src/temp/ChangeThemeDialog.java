package temp;

import javax.swing.*;
import java.awt.*;

public class ChangeThemeDialog extends JDialog {

    public ChangeThemeDialog() {
        super(MainFrame.getInstance(), "Change theme", true);

        setSize(250, 250); //todo: add scaling
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        JLabel label = new JLabel("Change theme:");

        add(label);

        setVisible(true);

    }
}
