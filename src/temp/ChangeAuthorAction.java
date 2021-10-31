package temp;

import java.awt.event.ActionEvent;

public class ChangeAuthorAction extends AbstractRudokAction{
    public ChangeAuthorAction() {
        putValue(NAME, "Change author");
        putValue(SHORT_DESCRIPTION, "Change author");
        putValue(SMALL_ICON, loadIcon("/res/icons/document_empty.png")); //todo

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new ChangeAuthorDialog();
        //todo
    }
}
