package main;

import action.ActionManager;
import action.dialog.OnStartDialog;
import command.CommandManager;
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
    private final CommandManager commandManager = new CommandManager();
    private MyTree tree;
    private final ProjectView projectView = new ProjectView();
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

        setSize(screenWidth, screenHeight);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Rudok");

        MyMenu myMenu = new MyMenu();
        setJMenuBar(myMenu);

        MyToolbar myToolbar = new MyToolbar();
        add(myToolbar, BorderLayout.NORTH);

        Workspace workspace = new Workspace("workspace1");

        tree = new MyTree(new MyTreeNode(workspace));
        JScrollPane treeScrollPane = new JScrollPane(tree);
        treeScrollPane.setMinimumSize(new Dimension(200, screenHeight));

        WorkspaceView workspaceView = new WorkspaceView(projectView, workspace);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, workspaceView);
        add(splitPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public void setViewToTreeSelection() {
        //change view
        if (tree.getActiveProjectNode() != null && tree.getActiveProjectNode().getRuNode() != projectView.getProject()) {
            projectView.displayProject((Project) tree.getActiveProjectNode().getRuNode());
        }
        //select tab
        if (tree.getActivePresentationNode() != null) {
            projectView.setSelectedIndex(tree.getActivePresentationNode().getIndexOfThis());
        }
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

    public CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public void update(NotificationEvent notification) {
        JOptionPane.showMessageDialog(this, notification.getMessage());
    }

    public void onStart() {
        new OnStartDialog();
    }
}
