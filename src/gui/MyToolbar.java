package gui;

import main.MainFrame;

import javax.swing.*;
import java.awt.*;

public class MyToolbar extends JToolBar {

    public MyToolbar() {
        add(MainFrame.getInstance().getActionManager().getNewAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getInfoAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getChangeAuthorAction());
        add(MainFrame.getInstance().getActionManager().getChangeThemeAction());
        add(MainFrame.getInstance().getActionManager().getRenameAction());


        setFloatable(false);
        setBackground(MainFrame.MAIN_COLOR);
    }
}
