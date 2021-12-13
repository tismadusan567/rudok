package main;

import action.ActionManager;
import error.ErrorFactory;
import gui.tree.MyTree;
import gui.tree.MyTreeNode;
import model.NotificationEvent;
import model.Project;
import model.Workspace;
import gui.MyMenu;
import gui.MyToolbar;
import observer.ISubscriber;
import view.ProjectView;
import view.WorkspaceView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements ISubscriber {
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
        if (instance == null) {
            instance = new MainFrame();
            instance.init();
        }
        return instance;
    }

    private void init() {
        ErrorFactory.getInstance().addSubscriber(this);

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setSize(screenWidth / 2, screenHeight / 2);
//        Image img = kit.getImage("images/iko.jpg");
//        setIconImage(img);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Rudok");

        myMenu = new MyMenu();
        setJMenuBar(myMenu);

        myToolbar = new MyToolbar();
        add(myToolbar, BorderLayout.NORTH);

        Workspace workspace = new Workspace("workspace1", null);

        tree = new MyTree(new MyTreeNode(workspace));
        JScrollPane treeScrollPane = new JScrollPane(tree);
        treeScrollPane.setMinimumSize(new Dimension(200, screenHeight));

        projectView = new ProjectView();
        WorkspaceView workspaceView = new WorkspaceView(projectView, workspace);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, workspaceView);
        add(splitPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public void setViewToTreeSelection() {
        //change view
        if (tree.getActiveProjectNode() != null && tree.getActiveProjectNode().getRuNode() != projectView.getProject()) {
            projectView.setChangeListenerPaused(true);
            projectView.displayProject((Project) tree.getActiveProjectNode().getRuNode());
            projectView.setChangeListenerPaused(false);
        }
        //select tab
        if (tree.getActivePresentationNode() != null) {
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

    public ProjectView getProjectView() {
        return projectView;
    }

    @Override
    public void update(NotificationEvent notification) {
        JOptionPane.showMessageDialog(this, notification.getMessage());
    }
}
