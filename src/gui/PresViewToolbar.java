package gui;

import main.MainFrame;
import model.slot.SlotType;

import javax.swing.*;
import java.awt.*;

public class PresViewToolbar extends JToolBar {
    public PresViewToolbar(JSpinner jSpinner, JCheckBox jCheckBox, JComboBox<SlotType> slotTypeComboBox) {
        add(MainFrame.getInstance().getActionManager().getAddStateAction());
        add(MainFrame.getInstance().getActionManager().getRemoveStateAction());
        add(MainFrame.getInstance().getActionManager().getMoveStateAction());
        add(MainFrame.getInstance().getActionManager().getSelectStateAction());
        addSeparator();

        add(MainFrame.getInstance().getActionManager().getSlideShowStateAction());
        addSeparator();

        add(MainFrame.getInstance().getActionManager().getChooseColorAction());
        addSeparator();

        add(new JLabel("Stroke width:"));
        jSpinner.setMaximumSize(new Dimension(50, 50));
        add(jSpinner);
        addSeparator();

        add(jCheckBox);
        addSeparator();

        slotTypeComboBox.setMaximumSize(new Dimension(100, 50));
        add(new JLabel("Slot type:"));
        add(slotTypeComboBox);
        addSeparator();

        add(MainFrame.getInstance().getActionManager().getEditSlotAction());

        setFloatable(false);
        setBackground(MainFrame.MAIN_COLOR);
    }
}
