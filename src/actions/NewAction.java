package actions;

import gui.tree.MyTree;
import gui.tree.MyTreeNode;
import main.MainFrame;
import model.*;

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

        MyTreeNode target = tree.getActiveNode();
        RuNode targetRuNode = target.getRuNode();
        RuNode newNode = null;

        if(targetRuNode instanceof Slide) return;

//        int index = target.getChildCount() + 1;
        int index = ((RuNodeComposite) targetRuNode).getLastChild();

        if(targetRuNode instanceof Presentation) {
            newNode = new Slide("Slide" + index, targetRuNode, index);

            System.out.println("add slide to " + targetRuNode);
        } else if(targetRuNode instanceof Project) {
            newNode = new Presentation("Presentation" + index, targetRuNode, "Author", "/backgrounds/background.jpeg");
//            new ChangeAuthorDialog(newPresentation); //optional for setting author name at creation

            System.out.println("add presentation to " + targetRuNode);
        } else {
            newNode = new Project("Project" + index, targetRuNode);

            System.out.println("add project to " + targetRuNode);
        }

        ((RuNodeComposite)targetRuNode).addChild(newNode);
        target.add(new MyTreeNode(newNode));
        if(targetRuNode instanceof Project) MainFrame.getInstance().selectProjectViewLastTab();


        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getTree());
    }
}
