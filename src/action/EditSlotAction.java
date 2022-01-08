package action;

import action.dialog.EditImageSlotDialog;
import action.dialog.EditTextSlotDialog;
import error.ErrorFactory;
import error.ErrorType;
import main.MainFrame;
import model.slot.Slot;
import view.PresentationView;

import java.awt.event.ActionEvent;

public class EditSlotAction extends AbstractRudokAction {
    public EditSlotAction() {
        putValue(NAME, "Edit slot");
        putValue(SHORT_DESCRIPTION, "Edit slot");
//        putValue(SMALL_ICON, loadIcon("/icons/icons32/painters_palette_brush.png"));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PresentationView pv = MainFrame.getInstance().getProjectView().getPresentationView();
        Slot slot;
        if (pv == null || (slot = pv.getPresentation().getSelectedSlot()) == null) {
            ErrorFactory.getInstance().generateError(ErrorType.NO_SLOT_SELECTED);
            return;
        }
        switch (slot.getSlotType()) {
            case TEXT -> new EditTextSlotDialog(slot);
            case IMAGE -> new EditImageSlotDialog(slot);
        }
    }
}
