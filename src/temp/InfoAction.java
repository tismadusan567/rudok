package temp;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class InfoAction extends AbstractRudokAction{
    public InfoAction() {
//        System.out.println("Working Directory = " + System.getProperty("user.dir"));

        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "Info");
        putValue(SMALL_ICON, loadIcon("icons/redGhost.png"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object[] dialogMessage = new Object[2];
        dialogMessage[0] = "Dusan Tisma RN20/20";
        dialogMessage[1] = loadIcon("icons/redGhost.png");
        JOptionPane.showMessageDialog(MainFrame.getInstance(), dialogMessage);
    }
}
