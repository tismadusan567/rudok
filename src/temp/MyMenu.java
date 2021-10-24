package temp;

import javax.swing.*;
import java.awt.*;

public class MyMenu extends JMenuBar {

    public MyMenu() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewAction());

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMaximumSize(new Dimension(50, 50)); //doesnt work
        helpMenu.add(MainFrame.getInstance().getActionManager().getInfoAction());

        add(fileMenu);
        add(helpMenu);
    }
}
