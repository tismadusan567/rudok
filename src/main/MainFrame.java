package main;

import action.ActionManager;
import gui.tree.MyTree;
import gui.tree.MyTreeNode;
import model.Project;
import model.Workspace;
import gui.MyMenu;
import gui.MyToolbar;
import view.ProjectView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private static MainFrame instance = null;
    private final ActionManager actionManager = new ActionManager();
    private MyTree tree;
    private ProjectView projectView;
    private MyMenu myMenu;
    private MyToolbar myToolbar;
    public static final Color MAIN_COLOR = new Color(161, 192, 87);

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
        int screenHeight = screenSize.height / 2;
        int screenWidth = screenSize.width / 2;

        setSize(screenWidth, screenHeight);
//        Image img = kit.getImage("images/iko.jpg");
//        setIconImage(img);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Rudok");

        myMenu = new MyMenu();
        setJMenuBar(myMenu);

        myToolbar = new MyToolbar(); //todo: add menu toolbar tree etc. to fields
        add(myToolbar, BorderLayout.NORTH);

        Workspace workspace = new Workspace("workspace1", null);
        tree = new MyTree(new MyTreeNode(workspace));

        JScrollPane treeScrollPane = new JScrollPane(tree);
        treeScrollPane.setMinimumSize(new Dimension(200, screenHeight));

        projectView = new ProjectView();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, projectView);
        add(splitPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public void setViewToTreeSelection() {
        //change view
        if(tree.getActiveProjectNode() != null && tree.getActiveProjectNode().getRuNode() != projectView.getProject()) {
            projectView.setChangeListenerPaused(true);
            projectView.displayProject((Project) tree.getActiveProjectNode().getRuNode());
            projectView.setChangeListenerPaused(false);
        }
        //select tab
        if(tree.getActivePresentationNode() != null) {
            projectView.setSelectedIndex(tree.getActivePresentationNode().getIndexOfThis());
        }
        //todo: scroll to slide
    }

    public void selectProjectViewLastTab() {
        projectView.setSelectedIndex(projectView.getTabCount() - 1);
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public MyTree getTree() {
        return tree;
    }

}
