package main;

import actions.ActionManager;
import gui.tree.MyTree;
import gui.tree.MyTreeNode;
import model.Presentation;
import model.Project;
import model.Slide;
import model.Workspace;
import gui.MyMenu;
import gui.MyToolbar;
import view.ProjectView;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class MainFrame extends JFrame {
    private static MainFrame instance = null;
    private final ActionManager actionManager = new ActionManager();
    private MyTree tree;
    private ProjectView projectView;

    //for testing
    public Presentation prez1;
    public Presentation prez2;
    public Project project;

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

        MyMenu myMenu = new MyMenu();
        setJMenuBar(myMenu);

        MyToolbar myToolbar = new MyToolbar(); //todo: add menu toolbar tree etc. to fields
        add(myToolbar, BorderLayout.NORTH);

        Workspace workspace = new Workspace("workspace1", null);
        project = new Project("projekat1", workspace);

        prez1 = new Presentation("prez1", project, "dusan", "/res/icons/background.jpeg");
        prez2 = new Presentation("prez2", project, "marko", "/res/icons/background2.jpg");
        prez1.add(new Slide("slide1", prez1, 0));
        prez2.add(new Slide("slajd1", prez2, 0));

        project.add(prez1);
        project.add(prez2);

        workspace.add(project);

        tree = new MyTree(new MyTreeNode(workspace));
        JScrollPane treeScrollPane = new JScrollPane(tree);
        treeScrollPane.setMinimumSize(new Dimension(screenWidth / 8, screenHeight));

        projectView = new ProjectView();
        projectView.displayProject(project);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, treeScrollPane, projectView);
        add(splitPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public void setViewToTreeSelection() {
        //change view
        if(tree.getActiveProjectNode() != null) {
            MainFrame.getInstance().getProjectView().displayProject((Project) tree.getActiveProjectNode().getRuNode());
        }
        if(tree.getActivePresentationNode() != null) {
            MainFrame.getInstance().getProjectView().setSelectedIndex(tree.getActivePresentationNode().getIndexOfThis());
        }
        //todo: scroll to slide
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
}
