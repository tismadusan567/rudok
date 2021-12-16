package gui;

import main.MainFrame;

import javax.swing.*;
import java.awt.*;

public class PresViewToolbar extends JToolBar {
    public PresViewToolbar(JSpinner jSpinner) {
        add(MainFrame.getInstance().getActionManager().getAddStateAction());
        add(MainFrame.getInstance().getActionManager().getRemoveStateAction());
        add(MainFrame.getInstance().getActionManager().getMoveStateAction());
        add(MainFrame.getInstance().getActionManager().getSelectStateAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getSlideShowStateAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getChooseColorAction());
        jSpinner.setMaximumSize(new Dimension(50, 50));
        add(jSpinner);

        setFloatable(false);
        setBackground(MainFrame.MAIN_COLOR);
    }
}
