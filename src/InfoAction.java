import javax.swing.*;
import java.awt.event.ActionEvent;

public class InfoAction extends AbstractRudokAction{
    public InfoAction() {
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "Info");
        putValue(MNEMONIC_KEY, "I");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object[] dialogMessage = new Object[2];
        dialogMessage[0] = "Dusan Tisma RN20/20";
        dialogMessage[1] = loadIcon("icons/redGhost.png");
//        dialogMessage[1] = new JButton("slika");
        JOptionPane.showMessageDialog(MainFrame.getInstance(), dialogMessage);
    }
}
