package view;

import javax.swing.*;
import java.awt.*;

public class MyMenu extends JMenuBar {

    public MyMenu() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewAction());

        JMenu editMenu = new JMenu("Edit");
        editMenu.add(MainFrame.getInstance().getActionManager().getChangeAuthorAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getChangeThemeAction());

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMaximumSize(new Dimension(50, 50)); //doesnt work
        helpMenu.add(MainFrame.getInstance().getActionManager().getInfoAction());

        add(fileMenu);
        add(editMenu);
        add(helpMenu);
    }
}
