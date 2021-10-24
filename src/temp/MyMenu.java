package temp;

import javax.swing.*;
import java.awt.*;

public class MyMenu extends JMenuBar {

    public MyMenu() {
        JMenu info = new JMenu("Info");
        info.setMaximumSize(new Dimension(50, 50)); //doesnt work
        info.add(MainFrame.getInstance().getActionManager().getInfoAction());
        add(info);
    }
}
