package actions;

import gui.tree.MyTree;
import gui.tree.MyTreeNode;
import main.MainFrame;
import model.Presentation;
import model.Project;
import model.Slide;
import model.Workspace;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class NewAction extends AbstractRudokAction{
    public NewAction() {
        putValue(NAME, "New");
        putValue(SHORT_DESCRIPTION, "New");
        putValue(SMALL_ICON, loadIcon("/res/icons/document_empty.png"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTree tree = MainFrame.getInstance().getTree();
        MyTreeNode activeSlide = tree.getActiveSlideNode();
        MyTreeNode activePresentation = tree.getActivePresentationNode();
        MyTreeNode activeProject = tree.getActiveProjectNode();
        MyTreeNode root =  tree.getRootNode();


        if(activeSlide != null) {
            System.out.println("Slides cant have children!");
        } else if(activePresentation != null) {
            int index = activePresentation.getChildCount() + 1;
            Slide newSlide = new Slide("Slide" + index, activePresentation.getRuNode(), 0);
            ((Presentation)activePresentation.getRuNode()).add(newSlide);
            activePresentation.add(new MyTreeNode(newSlide));

            System.out.println("add slide to " + activePresentation);
        } else if(activeProject != null) {
            int index = activeProject.getChildCount() + 1;
            Presentation newPresentation = new Presentation("Presentation" + index, activeProject.getRuNode(), "Author", "/backgrounds/background.jpeg");
//            new ChangeAuthorDialog(newPresentation); //optional for setting author name at creation
           ((Project) activeProject.getRuNode()).add(newPresentation);
           activeProject.add(new MyTreeNode(newPresentation));
           MainFrame.getInstance().selectProjectViewLastTab();

            System.out.println("add presentation to " + activeProject);
        } else {
            int index = root.getChildCount() + 1;
            Project newProject = new Project("Project" + index, root.getRuNode());
            ((Workspace)root.getRuNode()).add(newProject);
            root.add(new MyTreeNode(newProject));

            System.out.println("add project to " + root);
        }

        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());

//        if( activePresentation != null) (activePresentation.getRuNode()).setName("asd");

        //testing
//        MainFrame.getInstance().prez1.setAuthor("to da");

//        Presentation prez3 = new Presentation("prez3", MainFrame.getInstance().project, "milos", "/res/icons/background.jpeg");
//        prez3.add(new Slide("slide1", prez3, 0));
//        MainFrame.getInstance().project.add(prez3);

//        MainFrame.getInstance().prez1.setImagePath("/res/icons/background2.jpg");
    }
}
