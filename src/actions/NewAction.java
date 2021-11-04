package actions;

import gui.tree.MyTreeNode;
import main.MainFrame;
import model.Presentation;
import model.Project;
import model.Slide;
import model.Workspace;

import java.awt.event.ActionEvent;

public class NewAction extends AbstractRudokAction{
    public NewAction() {
        putValue(NAME, "New");
        putValue(SHORT_DESCRIPTION, "New");
        putValue(SMALL_ICON, loadIcon("/res/icons/document_empty.png"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //todo

        //testing
//        MainFrame.getInstance().prez1.setAuthor("to da");

//        Presentation prez3 = new Presentation("prez3", MainFrame.getInstance().project, "milos", "/res/icons/background.jpeg");
//        prez3.add(new Slide("slide1", prez3, 0));
//        MainFrame.getInstance().project.add(prez3);

//        MainFrame.getInstance().prez1.setImagePath("/res/icons/background2.jpg");
        System.out.println("new action");
    }
}
