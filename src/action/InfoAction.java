package action;

import main.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class InfoAction extends AbstractRudokAction {
    public InfoAction() {
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "Info");
        putValue(SMALL_ICON, loadIcon("/icons/icons32/information.png"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object[] dialogMessage = new Object[2];
        dialogMessage[0] = "Dusan Tisma RN20/20";
        dialogMessage[1] = loadIcon("/res/icons/ja.jpg");
        JOptionPane.showMessageDialog(MainFrame.getInstance(), dialogMessage);
    }
}
