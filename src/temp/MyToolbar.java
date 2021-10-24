package temp;

import javax.swing.*;
import java.awt.*;

public class MyToolbar extends JToolBar {

    public MyToolbar() {
        add(MainFrame.getInstance().getActionManager().getNewAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getInfoAction());

        setFloatable(false);
        setBackground(new Color(163, 227, 226));
    }
}
