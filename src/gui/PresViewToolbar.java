package gui;

import main.MainFrame;

import javax.swing.*;

public class PresViewToolbar extends JToolBar {
    public PresViewToolbar() {
        add(MainFrame.getInstance().getActionManager().getAddStateAction());
        add(MainFrame.getInstance().getActionManager().getRemoveStateAction());
        add(MainFrame.getInstance().getActionManager().getMoveStateAction());
        add(MainFrame.getInstance().getActionManager().getSelectStateAction());
        addSeparator();

        setFloatable(false);
        setBackground(MainFrame.MAIN_COLOR);
    }
}
