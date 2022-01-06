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
        add(MainFrame.getInstance().getActionManager().getSaveAction());
        addSeparator();

        add(MainFrame.getInstance().getActionManager().getOpenWorkspaceAction());
        add(MainFrame.getInstance().getActionManager().getOpenProjectAction());
        add(MainFrame.getInstance().getActionManager().getOpenPresentationAction());
        addSeparator();

        add(MainFrame.getInstance().getActionManager().getInfoAction());

        setFloatable(false);
        setBackground(MainFrame.MAIN_COLOR);
    }
}
