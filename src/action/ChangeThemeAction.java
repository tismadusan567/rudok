package action;

import action.dialog.ChangeThemeDialog;
import error.ErrorFactory;
import error.ErrorType;
import gui.tree.MyTreeNode;
import main.MainFrame;
import model.Presentation;

import java.awt.event.ActionEvent;

public class ChangeThemeAction extends AbstractRudokAction {
    public ChangeThemeAction() {
        putValue(NAME, "Change theme");
        putValue(SHORT_DESCRIPTION, "Change theme");
        putValue(SMALL_ICON, loadIcon("/icons/icons32/painters_palette_brush.png"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTreeNode activePresentationNode = MainFrame.getInstance().getTree().getActivePresentationNode();
        if (activePresentationNode == null) {
            ErrorFactory.getInstance().generateError(ErrorType.NO_PRESENTATION_SELECTED);
            return;
        }
        new ChangeThemeDialog((Presentation) activePresentationNode.getRuNode());
    }
}
