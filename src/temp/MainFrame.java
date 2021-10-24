package temp;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static MainFrame instance = null;
    private ActionManager actionManager = new ActionManager();

    private MainFrame() {

    }

    public static MainFrame getInstance() {
        if(instance == null) {
            instance = new MainFrame();
            instance.init();
        }
        return instance;
    }

    private void init() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setSize(screenWidth / 2, screenHeight / 2);
//        Image img = kit.getImage("images/iko.jpg");
//        setIconImage(img);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Rudok");

        MyMenu myMenu = new MyMenu();
        setJMenuBar(myMenu);
//
//        toolbar=new Toolbar();
//        add(toolbar, BorderLayout.NORTH);
        setVisible(true);
    }

    public ActionManager getActionManager() {
        return actionManager;
    }
}
