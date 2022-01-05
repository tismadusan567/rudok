package gui;

import main.MainFrame;

import javax.swing.*;

public class MyToolbar extends JToolBar {

    public MyToolbar() {
        add(MainFrame.getInstance().getActionManager().getNewAction());
        add(MainFrame.getInstance().getActionManager().getRemoveAction());
        add(MainFrame.getInstance().getActionManager().getRenameAction());
        addSeparator();

        add(MainFrame.getInstance().getActionManager().getChangeAuthorAction());
        add(MainFrame.getInstance().getActionManager().getChangeThemeAction());
        addSeparator();

        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());
        addSeparator();

        add(MainFrame.getInstance().getActionManager().getSaveProjectAction());
        add(MainFrame.getInstance().getActionManager().getOpenProjectAction());
        addSeparator();

        add(MainFrame.getInstance().getActionManager().getInfoAction());

        setFloatable(false);
        setBackground(MainFrame.MAIN_COLOR);
    }
}
