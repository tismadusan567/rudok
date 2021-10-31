package temp;

import java.awt.event.ActionEvent;

public class ChangeThemeAction extends AbstractRudokAction{
    public ChangeThemeAction() {
        putValue(NAME, "Change theme");
        putValue(SHORT_DESCRIPTION, "Change theme");
        putValue(SMALL_ICON, loadIcon("/res/icons/document_empty.png")); //todo

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //todo
    }
}
