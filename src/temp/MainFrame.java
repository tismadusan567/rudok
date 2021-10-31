package temp;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static MainFrame instance = null;
    private final ActionManager actionManager = new ActionManager();

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

        setSize(screenWidth, screenHeight);
//        Image img = kit.getImage("images/iko.jpg");
//        setIconImage(img);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Rudok");

        MyMenu myMenu = new MyMenu();
        setJMenuBar(myMenu);

        MyToolbar myToolbar = new MyToolbar();
        add(myToolbar, BorderLayout.NORTH);

        JScrollPane treeScrollPane = new JScrollPane(); //mozda napravi klasu za ovo kasnije ili dodaj u polja mainframea
        treeScrollPane.setMinimumSize(new Dimension(screenWidth / 8, screenHeight));
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, new JPanel());
        add(splitPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public ActionManager getActionManager() {
        return actionManager;
    }
}
